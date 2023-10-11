package com.bit.final_project.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class EmployeeDto {
    private BigDecimal salary;
    private String designation;
    private String type;
}
