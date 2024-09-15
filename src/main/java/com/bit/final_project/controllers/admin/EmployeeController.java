package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.dto.entityDto.CustomerDto;
import com.bit.final_project.dto.registrationDto.EmployeeRegisterDto;
import com.bit.final_project.mapper.CustomerMapper;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@RestController
@RequestMapping("api/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping
    public @ResponseBody
    ResponseEntity<StandardResponse> registration(@ModelAttribute EmployeeRegisterDto registerDto) throws IOException {
        UserDto userDTORequest= JSON.stringToObject(registerDto.getUserDto(),UserDto.class);
        EmployeeDto employeeDTORequest= JSON.stringToObject(registerDto.getEmployeeDto(),EmployeeDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",UserDto.init(employeeService.register(userDTORequest,employeeDTORequest, registerDto.getProImage()).getUser())),HttpStatus.OK
        );

    }
    @GetMapping(params ={"page","size"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getEmployee(@Param("@page") int page,@Param("@size") int size){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",employeeService.getAll(page,size)),HttpStatus.OK
        );

    }
    @GetMapping("/get")
    public @ResponseBody
    ResponseEntity<StandardResponse> getEmployee(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",EmployeeDto.init(employeeService.getEmployeeById(CurrentUser.getUser().getId()))),HttpStatus.OK
        );

    }
    @GetMapping("/get/{id}")
    public @ResponseBody
    ResponseEntity<StandardResponse> getEmployeeById(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",EmployeeDto.init(employeeService.getEmployeeById(id))),HttpStatus.OK
        );

    }
    @PutMapping("/update")
    public @ResponseBody
    ResponseEntity<StandardResponse> updateEmployee(@RequestBody EmployeeDto request){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",EmployeeDto.init(employeeService.updateProfile(request))),HttpStatus.OK
        );

    }
    @PutMapping("/image")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> updateProfilePic(@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", EmployeeDto.init(employeeService.updateProfilePic(file))),HttpStatus.OK
        );
    }
    @PostMapping("/test1")
    public @ResponseBody
    String test1() throws IOException {
        log.info("jjjjjjjjjjjjj");
        return "d";
    }

}
