package com.bit.final_project.models;

import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
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
    @Enumerated(EnumType.STRING)
    private PRODUCT_TYPE type;
    private String offer;
    private int qty;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    @JoinColumn(name = "door_id", referencedColumnName = "id")
    private Door door;
    @OneToOne
    @JoinColumn(name = "window_id", referencedColumnName = "id")
    private Windows windows;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "user_id")
    private Employee employee;


}
