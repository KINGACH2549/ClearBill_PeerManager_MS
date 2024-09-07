package com.clearbill.peermanager.ms.mappers;

import com.clearbill.peermanager.ms.dto.customer.CustomerResponseDTO;
import com.clearbill.peermanager.ms.entities.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMappers {

    @Autowired
    ModelMapper modelMapper;

    public Customer CustomerResponseToCustomerEntity(CustomerResponseDTO customerResponseDTO){
        return modelMapper.map(customerResponseDTO,Customer.class);
    }

    public List<Customer> CustomerResponseToCustomerEntity(List<CustomerResponseDTO> customerResponseDTO){
        return modelMapper.map(customerResponseDTO, new TypeToken<List<Customer>>() {}.getType());
    }

    public CustomerResponseDTO CustomerEntityToCustomerResponse(Customer customer){
        return modelMapper.map(customer,CustomerResponseDTO.class);
    }

}
