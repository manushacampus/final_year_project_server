package com.bit.final_project.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Data
public class AssignEmployeeDto {
    String jobId;
    List<String> employeeList;
}
