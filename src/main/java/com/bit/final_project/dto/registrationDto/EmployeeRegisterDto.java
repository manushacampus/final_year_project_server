package com.bit.final_project.dto.registrationDto;

import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeRegisterDto {

    private String userDto;
    private String employeeDto;
    private MultipartFile proImage;
}
