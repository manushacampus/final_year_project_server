package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.JobDoorDto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.SupplierDto;
import com.bit.final_project.services.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee/supplier")
@Slf4j
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @PostMapping("")
    public ResponseEntity<StandardResponse> saveSupplier(@RequestBody SupplierDto supplierDto){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",SupplierDto.init(supplierService.saveSupplier(supplierDto))),HttpStatus.OK
        );
    }
    @GetMapping(params ={"page","size","status"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getAllSupplierByStatus(@Param("@page") int page,
                                                                    @Param("@size") int size,
                                                                    @Param("@status") String status){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",supplierService.getAllSupplierByStatus(page,size,status)),HttpStatus.OK
        );

    }
}
