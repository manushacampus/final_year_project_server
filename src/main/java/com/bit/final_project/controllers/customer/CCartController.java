package com.bit.final_project.controllers.customer;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.mapper.CartMapper;
import com.bit.final_project.services.CartService;
import com.bit.final_project.services.OrderService;
import com.bit.final_project.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customer/cart")
@Slf4j
public class CCartController {
    @Autowired
    StockService stockService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @GetMapping(value = "/add")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> addToCart(@RequestParam("id") String id,
                                               @RequestParam("qty") int qty){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", CartMapper.convertToDTO(cartService.addToCart(id,qty))),HttpStatus.OK
        );
    }
    @GetMapping(value = "/all")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getAllCart(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", cartService.getAllCartByCustomer().stream().map(CartMapper::convertToDTO).collect(Collectors.toList())),HttpStatus.OK
        );
    }
    @PutMapping(value = "/change/qty")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> changeQty(@RequestParam("id") String id,
                                               @RequestParam("qty") Integer qty){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", CartMapper.convertToDTO(cartService.changeQty(id,qty))),HttpStatus.OK
        );
    }
    @DeleteMapping(value = "/delete")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> deleteCart(@RequestParam("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", CartMapper.convertToDTO(cartService.deleteCartById(id))),HttpStatus.OK
        );
    }
}
