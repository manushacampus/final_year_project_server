package com.bit.final_project.dto.entityDto;


import lombok.Data;


@Data
public class CartDto {
    private String id;
    private int qty;
    private CustomerDto customer;
    private StockItemDto stockItem;
}
