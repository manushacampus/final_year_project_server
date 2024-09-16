package com.bit.final_project.dto;

import lombok.Data;

@Data
public class RequestGrnDto {
    private String id;
    private int qty;
    private Double payment;
    private String invoiceNo;
    private String date;
    private String invoice;
}
