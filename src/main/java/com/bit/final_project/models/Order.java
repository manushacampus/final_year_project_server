package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private Double total;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private Feedback feedback;

}
