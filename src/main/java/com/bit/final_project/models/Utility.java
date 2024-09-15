package com.bit.final_project.models;

import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "utility")
@Getter
@Setter
public class Utility {
    @Id
    private String id;
    private String name;
    private String type;
    @Column(name = "bill_no")
    private String billNo;
    private LocalDate date;
    private Double payment;
    private String bill;
    @Enumerated(EnumType.STRING)
    private Status status;

}
