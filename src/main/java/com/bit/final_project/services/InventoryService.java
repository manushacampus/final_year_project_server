package com.bit.final_project.services;

import com.bit.final_project.dto.BarInventoryDto;
import com.bit.final_project.dto.entityDto.InventoryDto;
import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.models.Inventory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    Inventory getInventoryById(String id);
    BarInventoryDto createBarInventory(BarInventoryDto request);

    Page<InventoryDto> getInventoryByType(INVENTORY_TYPE type, int page, int size);

    List<InventoryDto> getAllInventoryByTypeAndKey(INVENTORY_TYPE type, String key);


}
