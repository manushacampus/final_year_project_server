package com.bit.final_project.dto;

import lombok.Data;

@Data
public class OtherInventoryDto {
    private String id;
    private String name;
    private String type;

    private String status;
    private int qty;
    private int price;
    private String inventoryType;
    private String code;
}
