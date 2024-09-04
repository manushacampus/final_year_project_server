package com.bit.final_project.mapper;

import com.bit.final_project.dto.UserDto;
import com.bit.final_project.dto.entityDto.CustomerDto;
import com.bit.final_project.models.Customer;

public class CustomerMapper {
    public static CustomerDto convertToDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUser(UserDto.init(customer.getUser()));
        return customerDto;
    }
}
