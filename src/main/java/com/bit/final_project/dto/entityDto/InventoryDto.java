package com.bit.final_project.dto.entityDto;

import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.models.Bar;
import lombok.Data;

import javax.persistence.*;

@Data
public class InventoryDto {

    private String id;
    private int qty;
    private int price;
    private String inventoryType;
    private BarDto bar;
}
