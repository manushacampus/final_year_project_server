package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee/customer")
@Slf4j
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping(params ={"page","size"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getCustomer(@Param("@page") int page, @Param("@size") int size){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",customerService.getCustomerByStatus(page,size,"ACTIVE")),HttpStatus.OK
        );

    }

}
