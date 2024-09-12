package com.bit.final_project.dto;

import lombok.Data;

@Data
public class BoardInventoryDto {
    private String id;
    private String name;
    private Double weight;
    private Double height;
    private Double width;
    private String color;
    private String type;
    private String dec;
    private int qty;
    private Double price;
    private String code;
}
