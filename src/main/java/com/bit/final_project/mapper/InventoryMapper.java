package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.BarDto;
import com.bit.final_project.dto.entityDto.BoardDto;
import com.bit.final_project.dto.entityDto.InventoryDto;
import com.bit.final_project.dto.entityDto.OtherDto;
import com.bit.final_project.models.Inventory;

public class InventoryMapper {
    public static InventoryDto convertToDto(Inventory inventory){
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setId(inventory.getId());
        inventoryDto.setQty(inventory.getQty());
        inventoryDto.setPrice(inventory.getPrice());
        inventoryDto.setInventoryType(String.valueOf(inventory.getInventoryType()));
        if (inventory.getBar() != null) {
            inventoryDto.setBar(BarDto.init(inventory.getBar()));
        }
        if (inventory.getBoard() != null) {
            inventoryDto.setBoard(BoardDto.init(inventory.getBoard()));
        }
        if (inventory.getOther() != null) {
            inventoryDto.setOther(OtherDto.init(inventory.getOther()));
        }
        return inventoryDto;
    }
}
