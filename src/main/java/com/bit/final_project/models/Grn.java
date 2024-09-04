package com.bit.final_project.models;

import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "grn")
@Slf4j
public class Grn {
    @Id
    private String id;
    private int qty;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "date")
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "purchase_request_id", referencedColumnName = "id")
    private PurchaseRequest purchaseRequest;
}
