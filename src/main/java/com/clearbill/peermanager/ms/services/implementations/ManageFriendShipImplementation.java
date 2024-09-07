package com.clearbill.peermanager.ms.services.implementations;

import com.clearbill.peermanager.ms.dto.customer.CustomerResponseDTO;
import com.clearbill.peermanager.ms.dto.frienship.FriendShipRequestDTO;
import com.clearbill.peermanager.ms.dto.frienship.FriendShipResponseDTO;
import com.clearbill.peermanager.ms.dto.frienship.ListOfFriendShipResponseDTO;
import com.clearbill.friendshipmanagement.entities.*;
import com.clearbill.peermanager.ms.entities.*;
import com.clearbill.peermanager.ms.exceptions.CustomerNotFoundException;
import com.clearbill.peermanager.ms.exceptions.FriendShipExistException;
import com.clearbill.peermanager.ms.exceptions.FriendShipNotFoundException;
import com.clearbill.peermanager.ms.exceptions.InvalidFriendShipActionException;
import com.clearbill.peermanager.ms.gateways.CustomerService;
import com.clearbill.peermanager.ms.mappers.CustomerMappers;
import com.clearbill.peermanager.ms.mappers.FriendShipMappers;
import com.clearbill.peermanager.ms.repository.FriendShipRepository;
import com.clearbill.peermanager.ms.services.ManageFriendShipServices;
import com.clearbill.peermanager.ms.utils.TimeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManageFriendShipImplementation implements ManageFriendShipServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageFriendShipImplementation.class);

    @Autowired
    FriendShipRepository friendShipRepository;

    @Autowired
    FriendShip friendShipEntity;

    @Autowired
    CustomerService customerService;

    @Autowired
    MetaData metaData;

    @Autowired
    Customer customer;

    @Autowired
    StatusHistory statusHistory;

    @Autowired
    Actions actions;

    @Autowired
    CustomerMappers customerMappers;


    @Autowired
    FriendShipMappers friendShipMappers;

    @Override
    public FriendShipResponseDTO addFriend(FriendShipRequestDTO friendShipRequestDTO) {

        try {


            List<Customer> customersInvolved = new ArrayList<>();
            List<CustomerResponseDTO> customerResponseDTO = customerService.getCustomerDetailsByCustomerIdOrEmailId(friendShipRequestDTO.getRequestedBy(),friendShipRequestDTO.getEmail());
            Map<String,String> customerMap=new HashMap<>();

            LOGGER.trace("Output from customer service");
            LOGGER.trace(customerResponseDTO.toString());

            // Make a call to customer service to search for customer with email or contact provided and return its response.

            if(customerResponseDTO.size()!=2) {
                LOGGER.error("Customer Not Found !!");
                throw new CustomerNotFoundException("Customer  Not Found !!!");
            }

            //Time related operations
            TimeUtils timeUtils = new TimeUtils();
            String currentTime = timeUtils.getCurrentTime();

            for(CustomerResponseDTO customerDetail : customerResponseDTO){
                if(customerDetail.getId().equalsIgnoreCase(friendShipRequestDTO.getRequestedBy())) {
                    customerDetail.setRole("creator");
                    customerDetail.setAccepted(true);
                    customerDetail.setJoinedGroupAt(currentTime);
                }
                customerMap.put(customerDetail.getRole(),customerDetail.getId());
            }

            List<Customer> customerDetails = customerMappers.CustomerResponseToCustomerEntity(customerResponseDTO);
            customersInvolved.addAll(customerDetails);
            LOGGER.info(customerDetails.toString());

            // Check if customers involved already has a friendship if they do then throw an error.

            Optional<FriendShip> friendShip = friendShipRepository.getFriendShipByCategoryAndListOfCustomers("individual",friendShipRequestDTO.getRequestedBy(),customerMap.get("acceptor"));

            if(friendShip.isPresent()){
                LOGGER.trace(friendShip.get().toString());
                String status = friendShip.get().getStatus();

                if(status.equalsIgnoreCase("active") || status.equalsIgnoreCase("pending")){
                    LOGGER.error("FriendShip Already Exist For customers :"+friendShipRequestDTO.getRequestedBy()+" and "+customerMap.get("acceptor"));
                    throw  new FriendShipExistException("FriendShip Already Exist for customers ");
                }else if(status.equalsIgnoreCase("blocked")){
                    LOGGER.error("FriendShip has status = blocked  :"+friendShipRequestDTO.getRequestedBy()+" and "+customerMap.get("acceptor")+" So new FriendShip creation is not allowed ");
                    throw new CustomerNotFoundException("Customer with Email "+friendShipRequestDTO.getEmail()+" Not Found !!!");
                }
            }

            // Make Data to be inserted in couch base and perform insert operation



            if(friendShipRequestDTO.getCategory().equalsIgnoreCase("individual"))  metaData.setAllowedIndividuals(2);

            statusHistory.setStatus("pending");
            statusHistory.setChangeTime(currentTime);

            actions.setActions(friendShipRequestDTO.getAction());
            actions.setRequestedBy(friendShipRequestDTO.getRequestedBy());
            actions.setStartTime(currentTime);

            friendShipEntity.setCategory(friendShipRequestDTO.getCategory());
            friendShipEntity.setStatus("pending");
            friendShipEntity.setCreatedByCustomer(friendShipRequestDTO.getRequestedBy());
            friendShipEntity.setMetaData(metaData);
            friendShipEntity.setAcceptedByCustomers(customersInvolved);
            friendShipEntity.setCreatedOn(currentTime);
            friendShipEntity.setUpdatedOn(currentTime);
            friendShipEntity.setActions(Collections.singletonList(actions));
            friendShipEntity.setStatusHistory(Collections.singletonList(statusHistory));


            // return the mapped data DTO
            FriendShipResponseDTO friendShipResponseDTO = friendShipMappers.FriendShipEntityToFriendShipResponse(friendShipRepository.save(friendShipEntity));

            return  friendShipResponseDTO;

        }catch (JsonProcessingException exception){
            LOGGER.error(exception.getMessage());
        }

        return null;
    }

    @Override
    public FriendShipResponseDTO updateFriendShip(FriendShipRequestDTO friendShipRequestDTO, String friendShipId) {

        try{


            List<Customer> customersInvolved = new ArrayList<>();
            CustomerResponseDTO customerResponseDTO = customerService.getCustomerDetailsByCustomerId(friendShipRequestDTO.getRequestedBy());


            customer = customerMappers.CustomerResponseToCustomerEntity(customerResponseDTO);

            customersInvolved.add(customer);
            LOGGER.info(customer.toString());

            friendShipEntity = friendShipRepository.findById(friendShipId).orElse(null);

            LOGGER.trace(friendShipEntity.toString());

            if(friendShipEntity==null){
                // throw error
                throw new FriendShipNotFoundException("FriendShip Not Found With Id "+friendShipId+" !!!!");
            }

            customersInvolved = friendShipEntity.getAcceptedByCustomers();

            Map<String, Customer> customerMap = customersInvolved.stream()
                    .collect(Collectors.toMap(Customer::getId, customer -> customer));


            //Time related operations
            TimeUtils timeUtils = new TimeUtils();
            String currentTime = timeUtils.getCurrentTime();

            friendShipEntity.setUpdatedOn(currentTime);

            actions.setActions(friendShipRequestDTO.getAction());
            actions.setRequestedBy(friendShipRequestDTO.getRequestedBy());
            actions.setStartTime(currentTime);

            friendShipEntity.updateActions(actions);


            if(friendShipRequestDTO.getAction().equalsIgnoreCase("accept") && friendShipEntity.getStatus().equalsIgnoreCase("pending")){

                friendShipEntity.setStatus("active");

                customerMap.get(friendShipRequestDTO.getRequestedBy()).setIsAccepted(true);
                customerMap.get(friendShipRequestDTO.getRequestedBy()).setJoinedGroupAt(currentTime);
                statusHistory.setStatus("active");

            }else if(friendShipRequestDTO.getAction().equalsIgnoreCase("block") && friendShipEntity.getStatus().equalsIgnoreCase("active")){

                // additional logic later to check if undone payments

                friendShipEntity.setStatus("blocked");
                statusHistory.setStatus("blocked");


            }else if(friendShipRequestDTO.getAction().equalsIgnoreCase("terminate") && friendShipEntity.getStatus().equalsIgnoreCase("active")){

                // additional logic later to check if undone payments

                friendShipEntity.setStatus("terminated");
                statusHistory.setStatus("terminated");

            }else if(friendShipRequestDTO.getAction().equalsIgnoreCase("unblock") && friendShipEntity.getStatus().equalsIgnoreCase("blocked")){

                friendShipEntity.setStatus("active");
                statusHistory.setStatus("active");

            }else{
                // throw error that action is this and current doc status is this which do not align with update
                throw new InvalidFriendShipActionException("FriendShip action = "+friendShipRequestDTO.getAction()+" does not align with current friendShip status");
            }

            // Update Customer Response DTO

            customerResponseDTO.setJoinedGroupAt(customerMap.get(friendShipRequestDTO.getRequestedBy()).getJoinedGroupAt());
            customerResponseDTO.setAccepted(customerMap.get(friendShipRequestDTO.getRequestedBy()).getIsAccepted());

            statusHistory.setChangeTime(currentTime);
            friendShipEntity.updateStatusHistory(statusHistory);
            friendShipRepository.save(friendShipEntity);

            FriendShipResponseDTO friendShipResponseDTO = friendShipMappers.FriendShipEntityToFriendShipResponse(friendShipEntity);

            return  friendShipResponseDTO;


        }catch (JsonProcessingException exception){
            LOGGER.error(exception.getMessage());
        }
        return null;
    }


    @Override
    public List<ListOfFriendShipResponseDTO> getFriendShipById(String friendShipId){

        friendShipEntity = friendShipRepository.findById(friendShipId).orElse(null);

        if(friendShipEntity==null){
            // throw error
            throw new FriendShipNotFoundException("FriendShip Not Found With Id "+friendShipId+" !!!!");
        }

        return friendShipMappers.ListOfFriendShipEntityToFriendShipResponse(Collections.singletonList(friendShipEntity));
    }

    @Override
    public List<ListOfFriendShipResponseDTO> getFriendShipByIdByCustomerId(HashMap<String, Object> requestParams) {

        try {


            List<FriendShip> friendShips = new ArrayList<>();

            if (requestParams.containsKey("customerId") && requestParams.containsKey("status")) {

                friendShips = friendShipRepository.getFriendShipsByCustomerId((String) requestParams.get("customerId"), (String) requestParams.get("status"));

            }

            if (friendShips.size() == 0) {

                throw new FriendShipNotFoundException("No FriendShips exists for customer " + requestParams.get("customerId"));
            }

            // Logic to handle field friendShip Details

            // Call customer MS to get all customers data

            Set<String> customerIds = new HashSet<>();
            Map<String, CustomerResponseDTO> customerMap = new HashMap<>();

            List<ListOfFriendShipResponseDTO> listOfFriendShipResponseDTO = friendShipMappers.ListOfFriendShipEntityToFriendShipResponse(friendShips);

            LOGGER.info(listOfFriendShipResponseDTO.toString());

            for (ListOfFriendShipResponseDTO friendShipDetail : listOfFriendShipResponseDTO) {

                for (Customer customerDetail : friendShipDetail.getCustomersInvolved()) {

                    if (!customerDetail.getId().equalsIgnoreCase((String) requestParams.get("customerId"))) {
                        if (!customerIds.contains(customerDetail.getId())) {
                           customerIds.add(customerDetail.getId());
                           customerMap.put(customerDetail.getId(), customerMappers.CustomerEntityToCustomerResponse(customerDetail));
                        }
                        friendShipDetail.updateFriendDetails(customerMap.get(customerDetail.getId()));
                    }
                }
            }

            List<CustomerResponseDTO> listOfCustomerResponseDTO = customerService.getCustomerDetailsByCustomerIds(customerIds);

            if(listOfCustomerResponseDTO.size()!=customerIds.size()){
                throw new CustomerNotFoundException("Customers not found!!");
            }

            for (CustomerResponseDTO customer : listOfCustomerResponseDTO) {

                if(customerMap.containsKey(customer.getId())) {
                    customerMap.get(customer.getId()).setCreatedOn(customer.getCreatedOn());
                    customerMap.get(customer.getId()).setName(customer.getName());
                    customerMap.get(customer.getId()).setProfilePicture(customer.getProfilePicture());
                }
            }

            return listOfFriendShipResponseDTO;

        }catch (JsonProcessingException exception){
            LOGGER.error(exception.getMessage());

        }

        return null;

    }

}
