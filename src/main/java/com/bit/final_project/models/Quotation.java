package com.bit.final_project.models;

import com.bit.final_project.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Slf4j
public class Quotation {
    @Id
    private String id;
    private Double total;
    private int qty;
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "customer_status")
    @Enumerated(EnumType.STRING)
    private CustomerOrderStatus customerStatus;
    @Enumerated(EnumType.STRING)
    private OrderStatus progress;
    @CreationTimestamp
    private Instant created_at;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private DESIGN_TYPE type;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "window_quot", referencedColumnName = "id")
    private WindowQuotation windowQuotation;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "door_quot", referencedColumnName = "id")
    private DoorQuotation doorQuotation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;
}
