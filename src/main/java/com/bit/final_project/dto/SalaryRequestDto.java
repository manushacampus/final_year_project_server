package com.bit.final_project.dto;

import lombok.Data;

@Data
public class SalaryRequestDto {
    private String id;
    private Double salary;
    private String date;
    private Double additional;
    private String invoice;
    private String status;
    private String employee;
}
