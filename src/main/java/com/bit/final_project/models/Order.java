package com.bit.final_project.models;

import com.bit.final_project.enums.CustomerOrderStatus;
import com.bit.final_project.enums.OrderStatus;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
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
    private Double total;
    @CreationTimestamp
    private Instant created_at;
    @UpdateTimestamp
    private Instant updated_at;
    @Enumerated(EnumType.STRING)
    private OrderStatus type;
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
