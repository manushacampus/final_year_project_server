package com.bit.final_project.dto.entityDto;

import com.bit.final_project.commons.URL;
import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.models.Salary;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class SalaryDto {
    private String id;
    private Double salary;
    private String date;
    private Double additional;
    private String invoice;
    private String status;
    private EmployeeDto employee;

    public static SalaryDto init(Salary salary){
        SalaryDto salaryDto = new SalaryDto();
        salaryDto.setId(salary.getId());
        salaryDto.setSalary(salary.getSalary());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        salaryDto.setDate(salary.getDate().format(formatter));
        salaryDto.setInvoice(URL.fileStorageUrl.replace("{type}","salary").replace("{fileName}",salary.getInvoice()));
        salaryDto.setAdditional(salary.getAdditional());
        salaryDto.setStatus(String.valueOf(salary.getStatus()));
        salaryDto.setEmployee(EmployeeDto.init(salary.getEmployee()));
        return salaryDto;
    }
}
