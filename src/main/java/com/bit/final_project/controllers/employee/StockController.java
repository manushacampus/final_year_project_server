package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee/stock")
@Slf4j
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("/{id}")
    ResponseEntity<StandardResponse> getStockByJob(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",stockService.getStockItemById(id)),HttpStatus.OK
        );
    }
    @GetMapping(params ={"page","size","status","type"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getAllStockItemByStatusAndType(@Param("@page") int page,
                                                  @Param("@size") int size,
                                                  @Param("@status") String status,
                                                  @Param("@type") String type){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",stockService.getAllStockItemByStatusAndType(page,size,status,type)),HttpStatus.OK
        );

    }
}
