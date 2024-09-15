package com.bit.final_project.dto.entityDto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
public class PaymentDto {
    private String id;
    private String invoice;
    private Double price;

    private String createdAt;

    private String updatedAt;

    private String paymentType;
    private String paymentStatus;

    private OrderDto order;

    private QuotationDto quotation;

    private CustomerDto customer;
}
