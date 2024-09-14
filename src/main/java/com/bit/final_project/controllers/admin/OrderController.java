package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.OrderCompleteDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.OrderDto;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping(params ={"page","size","status","type"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getAllStockItemByStatusAndType(@Param("@page") int page,
                                                                    @Param("@size") int size,
                                                                    @Param("@status") String status,
                                                                    @Param("@type") String type){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",orderService.getAllOrders(page,size,status,type)),HttpStatus.OK
        );

    }
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getOrderById(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", orderService.getOrderStockById(id)),HttpStatus.OK
        );
    }
    @PutMapping("/accept/{id}")
    public ResponseEntity<StandardResponse> acceptOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", OrderMapper.convertToDTO(orderService.acceptOrder(id))),HttpStatus.OK
        );
    }
    @PutMapping("/deliver/{id}")
    public ResponseEntity<StandardResponse> deliverOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", OrderMapper.convertToDTO(orderService.deliverOrder(id))),HttpStatus.OK
        );
    }
    @PutMapping("/delivered/{id}")
    public ResponseEntity<StandardResponse> deliveredOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", OrderMapper.convertToDTO(orderService.deliverOrder(id))),HttpStatus.OK
        );
    }
    @PutMapping("/complete/{id}")
    public ResponseEntity<StandardResponse> completeOrder(@PathVariable("id") String id,@ModelAttribute OrderCompleteDto request){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", OrderMapper.convertToDTO(orderService.completeOrder(id,request))),HttpStatus.OK
        );
    }
}
