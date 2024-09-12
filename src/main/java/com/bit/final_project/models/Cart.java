package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cart")
@Slf4j
public class Cart {
    @Id
    private String id;
    private int qty;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "stockItem")
    private StockItem stockItem;
}
