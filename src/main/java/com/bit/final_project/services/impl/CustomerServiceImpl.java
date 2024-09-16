package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.entityDto.CustomerDto;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.CustomerMapper;
import com.bit.final_project.models.Customer;
import com.bit.final_project.models.Design;
import com.bit.final_project.models.User;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.repositories.User.UserRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.CustomerService;
import com.bit.final_project.services.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    FilesStorageService filesStorageService;

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityExistsException("Customer not found with id: " + id));
    }

    @Override
    public Customer getCustomer() {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        return customer;
    }

    @Override
    public Customer updateCustomer(CustomerDto customer) {
        Customer customerResponse = customerRepository.findByUser(CurrentUser.getUser());
        User user = userService.getUserById(customerResponse.getUser_id());
        user.setFirst_name(customer.getUser().getFirstName());
        user.setLast_name(customer.getUser().getLastName());
        user.setAddress(customer.getUser().getAddress());
        user.setGender(customer.getUser().getGender());

        String currentEmail = user.getEmail();
        String newEmail = customer.getUser().getEmail();

        if (!currentEmail.equals(newEmail)) {
            User existingUser = userRepository.findByEmail(newEmail);
            if (existingUser != null && !existingUser.getId().equals(user.getId())) {
                throw new EntityExistsException("Email already exists: " + newEmail);
            }
            user.setEmail(customer.getUser().getEmail());
        }

        user.setContact(customer.getUser().getContact());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setBirthday(LocalDate.parse(customer.getUser().getDob(),formatter));


        userRepository.save(user);
        return customerResponse;
    }

    @Override
    public Customer updateProfilePic(MultipartFile request) throws IOException {
        Customer customerResponse = customerRepository.findByUser(CurrentUser.getUser());
        User user = userService.getUserById(customerResponse.getUser_id());
        if (request==null || request.isEmpty()){
            throw new BadRequestException("Profile Image is empty!");
        }
        String extension= FilenameUtils.getExtension(request.getOriginalFilename());
        AppFile appImage = new AppFile(
                "customer",
                Generator.getUUID(),
                extension,
                request.getInputStream()
        );
        AppFile saveProductDesignImage=filesStorageService.save(appImage);
        user.setImage(saveProductDesignImage.getImageName());
        userRepository.save(user);
        return customerResponse;
    }

    @Override
    public Page<CustomerDto> getCustomerByStatus(int page, int size, String status) {
        Pageable pageableRequest = PageRequest.of(page,size);
        return customerRepository.findAll(pageableRequest).map(CustomerMapper::convertToDTO);
    }
}
