package com.bit.final_project.models;

import com.bit.final_project.enums.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    @Column(name = "order_code")
    private String orderCode;
    private Double total;
    @CreationTimestamp
    private Instant created_at;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus type;
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "customer_status")
    @Enumerated(EnumType.STRING)
    private CustomerOrderStatus customerStatus;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private Feedback feedback;

}
