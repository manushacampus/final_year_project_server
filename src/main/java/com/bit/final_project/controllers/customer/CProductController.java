package com.bit.final_project.controllers.customer;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.StockItemDto;
import com.bit.final_project.mapper.CartMapper;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.models.Cart;
import com.bit.final_project.models.Order;
import com.bit.final_project.services.CartService;
import com.bit.final_project.services.OrderService;
import com.bit.final_project.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customer/product")
@Slf4j
public class CProductController {
    @Autowired
    StockService stockService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> orderProduct(@RequestParam("id") String id,
                                                  @RequestParam("qty") int qty){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", OrderMapper.convertToDTO(orderService.orderProduct(id,qty))),HttpStatus.OK
        );
    }
    @PostMapping(value = "/order/cart")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> orderProductByCart(@RequestBody List<Cart> cart){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", OrderMapper.convertToDTO(orderService.orderProductCart(cart))),HttpStatus.OK
        );
    }
    @GetMapping(value = "/add-cart")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> addToCart(@RequestParam("id") String id,
                                                       @RequestParam("qty") int qty){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", CartMapper.convertToDTO(cartService.addToCart(id,qty))),HttpStatus.OK
        );
    }
    @GetMapping(value = "/all-cart")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getAllCart(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", cartService.getAllCartByCustomer().stream().map(CartMapper::convertToDTO).collect(Collectors.toList())),HttpStatus.OK
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
