package com.bit.final_project.controllers.customer;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.CustomerDto;
import com.bit.final_project.mapper.CartMapper;
import com.bit.final_project.mapper.CustomerMapper;
import com.bit.final_project.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customer")
@Slf4j
public class CCustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/get")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getCustomer(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", CustomerMapper.convertToDTO(customerService.getCustomer())),HttpStatus.OK
        );
    }
    @PutMapping()
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDto request){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", CustomerMapper.convertToDTO(customerService.updateCustomer(request))),HttpStatus.OK
        );
    }
    @PutMapping("/image")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> updateProfilePic(@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", CustomerMapper.convertToDTO(customerService.updateProfilePic(file))),HttpStatus.OK
        );
    }
}
