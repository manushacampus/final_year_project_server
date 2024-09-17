package com.bit.final_project.controllers.customer;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.OrderStockDto;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.mapper.StockItemMapper;
import com.bit.final_project.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customer/order")
@Slf4j
public class COrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(params ={"page","size","status","type"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getAllStockItemByStatusAndType(@Param("@page") int page,
                                                                    @Param("@size") int size,
                                                                    @Param("@status") String status,
                                                                    @Param("@type") String type){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",orderService.getAllOrdersByCustomer(page,size,status,type)),HttpStatus.OK
        );

    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<StandardResponse> cancelOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", OrderMapper.convertToDTO(orderService.cancelOrder(id))),HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<StandardResponse> getProductByOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", orderService.getStockByOrder(id).stream().map(StockItemMapper::convertToDto).collect(Collectors.toList())),HttpStatus.OK
        );

    }
}
