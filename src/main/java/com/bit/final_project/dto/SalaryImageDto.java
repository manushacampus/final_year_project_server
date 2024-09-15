package com.bit.final_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class SalaryImageDto {

    private String salaryDto;
    private String employeeId;
    private MultipartFile bill;
}
