package com.bit.final_project.dto.entityDto;

import lombok.Data;

@Data
public class DesignInventoryDto {

    private String id;
    private DesignDto design;
    private  InventoryDto inventory;
}
