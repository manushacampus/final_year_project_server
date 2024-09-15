package com.bit.final_project.dto;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
public class EmployeeDto {
    private String user_id;
    private Double salary;
    private String designation;
    private String etf;
    private String type;
    private String status;
    private UserDto user;

    public static EmployeeDto init(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setUser_id(employee.getUser_id());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setType(employee.getType());
        employeeDto.setUser(UserDto.init(employee.getUser()));
        employeeDto.setStatus(String.valueOf(employee.getStatus()));
        employeeDto.setDesignation(String.valueOf(employee.getDesignation()));
        return employeeDto;
    }
}
