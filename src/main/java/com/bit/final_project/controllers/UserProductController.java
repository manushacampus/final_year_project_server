package com.bit.final_project.controllers;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.services.CartService;
import com.bit.final_project.services.OrderService;
import com.bit.final_project.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
@Slf4j
public class UserProductController {
    @Autowired
    StockService stockService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
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
