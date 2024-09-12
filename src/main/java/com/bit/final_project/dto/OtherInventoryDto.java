package com.bit.final_project.dto;

import lombok.Data;

@Data
public class OtherInventoryDto {
    private String id;
    private String code;
    private String name;
    private String color;
    private Double length;
    private Double  weight;
    private String type;
    private String status;
    private int qty;
    private Double price;
    private String inventoryType;

}
