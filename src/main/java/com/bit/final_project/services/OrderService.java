package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.OrderDto;
import com.bit.final_project.dto.entityDto.StockItemDto;
import com.bit.final_project.models.Cart;
import com.bit.final_project.models.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Order orderProduct(String id,int qty);
    Order orderProductCart(List<Cart> cart);
    Page<OrderDto> getAllOrdersByCustomer(int page, int size, String status, String orderType);
}
