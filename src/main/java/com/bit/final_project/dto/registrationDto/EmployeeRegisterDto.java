package com.bit.final_project.dto.registrationDto;

import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import lombok.Data;

@Data
public class EmployeeRegisterDto {

    private UserDto userDto;
    private EmployeeDto employeeDto;
}
