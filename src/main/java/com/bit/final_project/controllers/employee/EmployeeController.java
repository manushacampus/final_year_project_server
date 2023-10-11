package com.bit.final_project.controllers.employee;

import com.bit.final_project.dto.UserDto;
import com.bit.final_project.dto.registrationDto.EmployeeRegisterDto;
import com.bit.final_project.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping
    @Transactional
    public @ResponseBody
    UserDto registration(@RequestBody EmployeeRegisterDto registerDto){
        return UserDto.init(employeeService.register(registerDto).getUser());

    }

}
