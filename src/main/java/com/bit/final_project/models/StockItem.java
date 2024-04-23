package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "stock_item")
@Getter
@Setter
public class StockItem {
    @Id
    private String id;
    private String type;
    private String offer;
    @OneToOne
    @JoinColumn(name = "door_id", referencedColumnName = "id")
    private Door door;
    @OneToOne
    @JoinColumn(name = "window_id", referencedColumnName = "id")
    private Windows windows;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "user_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

}
