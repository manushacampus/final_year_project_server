package com.bit.final_project.dto.entityDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private String id;
    private String invoice;
    private Double price;


    private String paymentType;

    private OrderDto order;

    private QuotationDto quotation;

    private CustomerDto customer;
}
