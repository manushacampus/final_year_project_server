package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.BarInventoryDto;
import com.bit.final_project.dto.entityDto.InventoryDto;
import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.InventoryMapper;
import com.bit.final_project.models.Bar;
import com.bit.final_project.models.BarAngles;
import com.bit.final_project.models.Design;
import com.bit.final_project.models.Inventory;
import com.bit.final_project.repositories.Bar.BarRepository;
import com.bit.final_project.repositories.Inventory.InventoryRepository;
import com.bit.final_project.services.BarAnglesService;
import com.bit.final_project.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    BarRepository barRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    BarAnglesService barAnglesService;

    @Override
    public Inventory getInventoryById(String id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new EntityExistsException("inventory not found with id: " + id));
    }

    @Override
    @Transactional
    public BarInventoryDto createBarInventory(BarInventoryDto request) {
        BarAngles barAngles = barAnglesService.getBarAnglesBySectionNo(request.getSectionNo(), "");
        Bar bar = new Bar();
        String uuid;
        do {
            uuid = Generator.getUUID();
        } while (barRepository.existsById(uuid));

        bar.setId(uuid);
        bar.setBarAngles(barAngles);
        bar.setLength(request.getLength());
        bar.setColor(request.getColor());
        Bar barRes = barRepository.save(bar);

        do {
            uuid = Generator.getUUID();
        } while (inventoryRepository.existsById(uuid));
        Inventory inventory= new Inventory();
        inventory.setId(Generator.getUUID());
        inventory.setBar(barRes);
        inventory.setQty(request.getQty());
        inventory.setPrice(request.getPrice());
        inventory.setInventoryType(INVENTORY_TYPE.BAR);
        Inventory inventoryRes= inventoryRepository.save(inventory);

        BarInventoryDto barInventoryDto = new BarInventoryDto();
        barInventoryDto.setSectionNo(barRes.getBarAngles().getSectionNo());
        return barInventoryDto;
    }

    @Override
    public Page<InventoryDto> getInventoryByType(INVENTORY_TYPE type, int page, int size) {
        Pageable pageableRequest = PageRequest.of(page,size);
        return inventoryRepository.findByInventoryType(pageableRequest,type).map(InventoryMapper::convertToDto);
    }
    @Override
    public List<InventoryDto> getAllInventoryByTypeAndKey(INVENTORY_TYPE type,String key){
        return inventoryRepository.findByInventoryTypeAndCode(type,key).stream().map(InventoryMapper::convertToDto).collect(Collectors.toList());
    }
}
