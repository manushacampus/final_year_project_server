package com.bit.final_project.services;

import com.bit.final_project.dto.OrderCompleteDto;
import com.bit.final_project.dto.OrderStockDto;
import com.bit.final_project.dto.entityDto.OrderDto;

import com.bit.final_project.models.Cart;
import com.bit.final_project.models.Order;

import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    Order getOrderById(String id);
    Order orderProduct(String id,int qty);
    Order orderProductCart(List<Cart> cart);
    Page<OrderDto> getAllOrdersByCustomer(int page, int size, String status, String orderType);
    Page<OrderDto> getAllOrders(int page, int size, String status, String orderType);

    OrderStockDto getOrderStockById(String id);

    Order acceptOrder(String orderId);
    Order deliverOrder(String orderId);
    Order deliveredOrder(String orderId);
    Order cancelOrder(String orderId);
    Order completeOrder(String orderId,OrderCompleteDto request) throws IOException;
}
