package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order_stock")
public class OrderStock {
    @Id
    private String id;
    private int qty;
    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private StockItem stockItem;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
