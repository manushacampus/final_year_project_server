package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.DesignAndInventoryDto;
import com.bit.final_project.dto.DesignRequestDto;
import com.bit.final_project.dto.entityDto.DesignDto;
import com.bit.final_project.mapper.DesignInventoryMapper;
import com.bit.final_project.mapper.DesignMapper;
import com.bit.final_project.mapper.InventoryMapper;
import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.services.DesignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee/product-design")
@Slf4j
public class ProductDesignController {
    @Autowired
    DesignService designService;

    @PostMapping("")
    @Transactional
    public ResponseEntity<StandardResponse> createProductDesign(@ModelAttribute DesignRequestDto request) throws IOException {
        DesignDto designDtoRequest= JSON.stringToObject(request.getDesignDto(),DesignDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",designService.create(designDtoRequest, request.getImage())),HttpStatus.OK
        );
    }

    @PostMapping("add-inventory")
    @Transactional
    public ResponseEntity<StandardResponse> addInventoryForDesign(@RequestBody DesignAndInventoryDto request) throws IOException {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", InventoryMapper.convertToDto(designService.addInventoryForDesign(request))),HttpStatus.OK
        );
    }
    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> deleteInventoryForDesign(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", InventoryMapper.convertToDto(designService.deleteById(id).getInventory())),HttpStatus.OK
        );
    }


    @GetMapping(params = {"status","type","page", "size"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getAllPage(@Param("@status") String status,
                                            @Param("@type") String type,
                                            @Param("@page") int page,
                                            @Param("@size") int size) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", designService.getALlDesignByStatusPage(status,page,size)),HttpStatus.OK
        );
    }

    @GetMapping(value = "/get-inventory", params = {"designId"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getInventoryByDesign(@Param("@designId") String designId) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", designService.getInventoryByDesign(designId).stream().map(DesignInventoryMapper::convertToDto).collect(Collectors.toList())),HttpStatus.OK
        );
    }

    @GetMapping(value = "/get-design", params = {"designId"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getDesignById(@Param("@designId") String designId) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", DesignMapper.convertToDTO(designService.getDesignById(designId))),HttpStatus.OK
        );
    }

    @PostMapping(value = "/status")
    public @ResponseBody
    ResponseEntity<StandardResponse> changeStatus(@RequestParam("status") String status,
                                                  @RequestParam("id") String id) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",DesignMapper.convertToDTO(designService.changeStatus(status,id))),HttpStatus.OK
        );
    }

}
