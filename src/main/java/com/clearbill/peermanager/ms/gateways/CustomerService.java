package com.clearbill.peermanager.ms.gateways;

import com.clearbill.peermanager.ms.dto.customer.CustomerResponseDTO;
import com.clearbill.peermanager.ms.exceptions.CustomerNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
public class CustomerService {
     private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

     @Autowired
     private ModelMapper modelMapper;

     @Autowired
     private ObjectMapper objectMapper;



     private WebClient webClient;

     public CustomerService(){

           webClient = WebClient.builder()
                  .baseUrl("http://localhost:8081") // Set your desired base URL here
                  .build();
     }




     public CustomerResponseDTO getCustomerDetailsByEmailId(String emailID) throws JsonProcessingException {

          String response = webClient.get().uri("/v1/customerManagement/customerDetails/queryCustomer?email="+emailID).retrieve().onStatus(HttpStatus.NOT_FOUND::equals, error -> error.bodyToMono(String.class)
                  .flatMap(errorBody -> {
                       LOGGER.error("Error received from customer service :"+errorBody);
                       return Mono.error(new CustomerNotFoundException("Customer with Email "+emailID+" Not Found !!!"));
                  })).bodyToMono(String.class).block();


          return objectMapper.readValue(response, CustomerResponseDTO.class);

     }


     public CustomerResponseDTO getCustomerDetailsByCustomerId(String customerId) throws JsonProcessingException {

          String response = webClient.get().uri("/v1/customerManagement/customerDetails/queryCustomer?customerId="+customerId).retrieve().onStatus(HttpStatus.NOT_FOUND::equals, error -> error.bodyToMono(String.class)
                  .flatMap(errorBody -> {
                       LOGGER.error("Error received from customer service :"+errorBody);
                       return Mono.error(new CustomerNotFoundException("Customer with Customer Id "+customerId+" Not Found !!!"));
                  })).bodyToMono(String.class).block();


          return objectMapper.readValue(response, CustomerResponseDTO.class);
     }


    public List<CustomerResponseDTO> getCustomerDetailsByCustomerIdOrEmailId(String customerId, String emailId) throws JsonProcessingException {

        String response = webClient.get().uri("/v1/customerManagement/customerDetails/queryCustomers?requestedCustomerId="+customerId+"&targetCustomerEmail="+emailId).retrieve().onStatus(HttpStatus.NOT_FOUND::equals, error -> error.bodyToMono(String.class)
                .flatMap(errorBody -> {
                    LOGGER.error("Error received from customer service :"+errorBody);
                    return Mono.error(new CustomerNotFoundException("Customer with Customer Id "+customerId+" or email "+emailId+" Not Found !!!"));
                })).bodyToMono(String.class).block();


        return  objectMapper.readValue(response,
                new TypeReference<List<CustomerResponseDTO>>() {});
    }

    public List<CustomerResponseDTO> getCustomerDetailsByCustomerIds(Set<String> customerIds) throws JsonProcessingException {

         String customers = String.join(",",customerIds);

        String response = webClient.get().uri("/v1/customerManagement/customerDetails/queryCustomers?customerIds="+customers).retrieve().onStatus(HttpStatus.NOT_FOUND::equals, error -> error.bodyToMono(String.class)
                .flatMap(errorBody -> {
                    LOGGER.error("Error received from customer service :"+errorBody);
                    return Mono.error(new CustomerNotFoundException("Customer with Customer Id's "+customers+" Not Found !!!"));
                })).bodyToMono(String.class).block();


        return  objectMapper.readValue(response,
                new TypeReference<List<CustomerResponseDTO>>() {});
    }


}
