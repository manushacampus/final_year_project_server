package com.bit.final_project.dto;

import com.bit.final_project.models.User;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class EmployeeDto {
    private BigDecimal salary;
    private String designation;
    private String type;
    private String etf;
    private String status;
    private UserDto user;
}
