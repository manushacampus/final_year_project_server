package com.bit.final_project.dto.entityDto;

import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.Job;
import com.bit.final_project.models.JobEmployee;
import lombok.Data;

import javax.persistence.*;
@Data
public class JobEmployeeDto {
    private String id;
    private String status;
    private String process_type;
    private JobDto job;
    private EmployeeDto employee;

    public static JobEmployeeDto init(JobEmployee jobEmployee){
        JobEmployeeDto jobEmployeeDto = new JobEmployeeDto();
        jobEmployeeDto.setId(jobEmployee.getId());
        jobEmployeeDto.setJob(JobDto.init(jobEmployee.getJob()));
        jobEmployeeDto.setEmployee(EmployeeDto.init(jobEmployee.getEmployee()));
        jobEmployeeDto.setProcess_type(jobEmployee.getProcess_type());
        return jobEmployeeDto;
    }
}
