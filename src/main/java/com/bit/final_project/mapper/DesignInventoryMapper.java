package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.BarDto;
import com.bit.final_project.dto.entityDto.DesignInventoryDto;
import com.bit.final_project.dto.entityDto.InventoryDto;
import com.bit.final_project.models.DesignInventory;


public class DesignInventoryMapper {

    public static DesignInventoryDto convertToDto(DesignInventory designInventory){
        DesignInventoryDto designInventoryDto = new DesignInventoryDto();
        designInventoryDto.setId(designInventory.getId());
        if (designInventory.getInventory() != null) {
            designInventoryDto.setInventory(InventoryMapper.convertToDto(designInventory.getInventory()));
        }
        if (designInventory.getDesign() != null) {
            designInventoryDto.setDesign(DesignMapper.convertToDTO(designInventory.getDesign()));
        }
        return designInventoryDto;
    }
}
