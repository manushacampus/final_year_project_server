package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.CustomerDto;
import com.bit.final_project.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CustomerService {
    Customer getCustomerById(String id);
    Customer getCustomer();

    Customer updateCustomer(CustomerDto customer);

    Customer updateProfilePic(MultipartFile request) throws IOException;

    Page<CustomerDto> getCustomerByStatus(int page, int size,String status);
}