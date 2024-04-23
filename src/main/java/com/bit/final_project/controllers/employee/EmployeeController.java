package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.dto.registrationDto.EmployeeRegisterDto;
import com.bit.final_project.mapper.EmployeeMapper;
import com.bit.final_project.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping
    public @ResponseBody
    ResponseEntity<StandardResponse> registration(@ModelAttribute EmployeeRegisterDto registerDto){
        UserDto userDTORequest= JSON.stringToObject(registerDto.getUserDto(),UserDto.class);
        EmployeeDto employeeDTORequest= JSON.stringToObject(registerDto.getEmployeeDto(),EmployeeDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",UserDto.init(employeeService.register(userDTORequest,employeeDTORequest).getUser())),HttpStatus.OK
        );

    }
    @GetMapping(params ={"page","size"})
    public @ResponseBody
    ResponseEntity<StandardResponse> registration(@Param("@page") int page,@Param("@size") int size){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",employeeService.getAll(page,size)),HttpStatus.OK
        );

    }

}
