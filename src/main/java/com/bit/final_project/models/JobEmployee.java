package com.bit.final_project.models;

import com.bit.final_project.enums.Status;
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
    @Enumerated(EnumType.STRING)
    private Status status;
    private String process_type;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "user_id")
    private Employee employee;
}
