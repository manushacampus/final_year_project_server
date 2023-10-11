package com.bit.final_project.models;

import com.bit.final_project.dto.EmployeeDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Employee {
    public enum Status{
        ACTIVE, INACTIVE
    }
    @Id
    @Column(name = "user_id")
    private String user_id;
    private BigDecimal salary;
    private String designation;
    private String type;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public static Employee init(EmployeeDto request){
        Employee employee = new Employee();
        employee.setSalary(request.getSalary());
        employee.setDesignation(request.getDesignation());
        employee.setType(request.getType());
        employee.setStatus(Status.ACTIVE);

        return employee;
    }


}
