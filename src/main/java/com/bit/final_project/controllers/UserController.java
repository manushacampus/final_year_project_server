package com.bit.final_project.controllers;

import com.bit.final_project.dto.LoginDto;
import com.bit.final_project.dto.TestDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.EmployeeService;
import com.bit.final_project.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/register")
    public @ResponseBody
    UserDto register(@RequestBody UserDto request){
       User user = userService.register(request);
       UserDto userDto = new UserDto();
       userDto.setFirstName(user.getFirst_name());
       return userDto;
    }

    @PostMapping("/login")
    public @ResponseBody
    LoginDto login(@RequestBody UserDto request){
        log.info("controller login user={}",request.getEmail());
        User user=userService.login(request);
        CurrentUser.setUser(user, String.valueOf(user.getUser_role()));
        Employee employee = employeeService.getEmployeeByUser(user);
        LoginDto loginDto = LoginDto.init(user);
        loginDto.setDesignation(String.valueOf(employee.getDesignation()));
        return loginDto;
    }
    @PostMapping("/test")
    public @ResponseBody
    String test(@ModelAttribute TestDto request) throws IOException {
        log.info("jjjjjjjjjjjjj={}",request.getName());
        userService.test(request.getMultiImage());
        return "d";
    }
    @PostMapping("/test1")
    public @ResponseBody
    String test1(UserDto request) throws IOException {
        log.info("jjjjjjjjjjjjj={}",request.getLastName());
        return "d";
    }

}
