package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stock")
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
}
