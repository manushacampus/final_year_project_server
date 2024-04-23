package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "job_employee")
@Getter
@Setter
public class JobEmployee {
    @Id
    private String id;
    private String status;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "user_id")
    private Employee employee;
}
