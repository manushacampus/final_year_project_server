package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.AddBarAnglesDto;
import com.bit.final_project.dto.BarInventoryDto;
import com.bit.final_project.dto.entityDto.BarAnglesDto;
import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.services.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/inventory")
@Slf4j
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @PostMapping("/bar")
    public @ResponseBody
    ResponseEntity<StandardResponse> enterBar(@RequestBody BarInventoryDto request) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", inventoryService.createBarInventory(request)),HttpStatus.OK
        );
    }
    @GetMapping(params = {"type","page", "size"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getAll(@Param("@type") String type,@Param("@page") int page, @Param("@size") int size) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", inventoryService.getInventoryByType(INVENTORY_TYPE.valueOf(type),page,size)),HttpStatus.OK
        );
    }
}
