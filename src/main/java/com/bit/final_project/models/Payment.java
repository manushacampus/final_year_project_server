package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "payment")
@Slf4j
public class Payment {
    @Id
    private String id;
    private String invoice;
    @Column(name = "payment_type")
    private String paymentType;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private Customer customer;
}
