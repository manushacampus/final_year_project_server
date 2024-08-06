package com.bit.final_project.mapper;

import com.bit.final_project.commons.URL;
import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.models.Employee;

public class EmployeeMapper {
    public static EmployeeDto convertToDTO(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setDesignation(String.valueOf(employee.getDesignation()));
        dto.setType(employee.getType());
        dto.setStatus(String.valueOf(employee.getStatus()));
        if (employee.getUser() != null) {
            dto.setUser(UserDto.init(employee.getUser())); // Make sure getUser() doesn't trigger lazy load exception
            if (employee.getUser().getImage()!=null) {
                dto.getUser().setImage(URL.fileStorageUrl.replace("{type}", "employee").replace("{fileName}", employee.getUser().getImage()));
            }
        }
        return dto;
    }
}
