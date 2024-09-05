package com.bit.final_project.dto.entityDto;

import lombok.Data;


@Data
public class InventoryDto {

    private String id;
    private int qty;
    private int price;
    private String code;
    private String inventoryType;
    private BarDto bar;
    private BoardDto board;
    private OtherDto other;
}
