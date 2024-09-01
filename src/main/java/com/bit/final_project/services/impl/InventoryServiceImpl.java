package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.BarInventoryDto;
import com.bit.final_project.dto.BoardInventoryDto;
import com.bit.final_project.dto.OtherInventoryDto;
import com.bit.final_project.dto.entityDto.InventoryDto;
import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.InventoryMapper;
import com.bit.final_project.models.*;
import com.bit.final_project.repositories.Bar.BarRepository;
import com.bit.final_project.repositories.Board.BoardRepository;
import com.bit.final_project.repositories.Inventory.InventoryRepository;
import com.bit.final_project.repositories.Other.OtherRepository;
import com.bit.final_project.services.BarAnglesService;
import com.bit.final_project.services.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    BarRepository barRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    BarAnglesService barAnglesService;
    @Autowired
    OtherRepository otherRepository;

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
        inventory.setId(uuid);
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
    public Inventory createBoardInventory(BoardInventoryDto request) {
        log.info("ssdsdsd={}",request.getDec());
        String uuid;
        Board board = new Board();
        do {
            uuid = Generator.getUUID();
        } while (boardRepository.existsById(uuid));
        board.setId(uuid);
        board.setName(request.getName());
        board.setHeight(request.getHeight());
        board.setWidth(request.getWidth());
        board.setWeight(request.getWeight());
        board.setColor(request.getColor());
        board.setType(request.getType());
        board.setDescription(request.getDec());
        Board responseBoard = boardRepository.save(board);
        Inventory inventory= new Inventory();
        do {
            uuid = Generator.getUUID();
        } while (inventoryRepository.existsById(uuid));
        inventory.setId(uuid);
        inventory.setCode(request.getCode());
        inventory.setPrice(request.getPrice());
        inventory.setBoard(responseBoard);
        inventory.setInventoryType(INVENTORY_TYPE.BOARD);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory createOtherInventory(OtherInventoryDto request) {
        log.info("create Other Inventory={}",request.getCode());
        String uuid;
        Other other = new Other();
        do {
            uuid = Generator.getUUID();
        } while (otherRepository.existsById(uuid));
        other.setId(uuid);
        Other responseOther = otherRepository.save(other);
        Inventory inventory= new Inventory();
        do {
            uuid = Generator.getUUID();
        } while (inventoryRepository.existsById(uuid));
        inventory.setId(uuid);
        inventory.setCode(request.getCode());
        inventory.setPrice(request.getPrice());
        inventory.setOther(responseOther);
        inventory.setInventoryType(INVENTORY_TYPE.OTHER);
        return inventoryRepository.save(inventory);

    }

    @Override
    public Page<InventoryDto> getInventoryByType(INVENTORY_TYPE type, int page, int size) {
        Pageable pageableRequest = PageRequest.of(page,size);
        if (type.equals(INVENTORY_TYPE.ALL)){
            return inventoryRepository.findAll(pageableRequest).map(InventoryMapper::convertToDto);
        }
        return inventoryRepository.findByInventoryType(pageableRequest,type).map(InventoryMapper::convertToDto);
    }
    @Override
    public List<InventoryDto> getAllInventoryByTypeAndKey(INVENTORY_TYPE type,String key){
        return inventoryRepository.findByInventoryTypeAndCode(type,key).stream().map(InventoryMapper::convertToDto).collect(Collectors.toList());
    }
}
