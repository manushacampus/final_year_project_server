package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.OrderDto;
import com.bit.final_project.dto.entityDto.StockItemDto;
import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.models.*;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.repositories.Order.OrderRepository;
import com.bit.final_project.repositories.OrderStock.OrderStockRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.CartService;
import com.bit.final_project.services.OrderService;
import com.bit.final_project.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderStockRepository orderStockRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartService cartService;
    @Autowired
    StockService stockService;
    @Override
    public Order orderProduct(String id,int qty) {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        StockItem stockItem = stockService.getStockItemById(id);
        Order order = new Order();
        order.setId(Generator.getUUID());
        order.setCustomer(customer);
        order.setTotal(stockItem.getPrice());
        Order orderResponse = orderRepository.save(order);

        OrderStock orderStock = new OrderStock();
        orderStock.setId(Generator.getUUID());
        orderStock.setStockItem(stockItem);
        orderStock.setOrder(orderResponse);
        orderStockRepository.save(orderStock);

        return orderResponse;
    }

    @Override
    public Order orderProductCart(List<Cart> cart) {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        Order order = new Order();
        order.setId(Generator.getUUID());
        order.setCustomer(customer);
        Order orderResponse = orderRepository.save(order);
        if (orderResponse != null){
            for (Cart cartRe : cart) {
                OrderStock orderStock = new OrderStock();
                orderStock.setId(Generator.getUUID());
                orderStock.setStockItem(cartRe.getStockItem());
                orderStock.setOrder(orderResponse);
                orderStockRepository.save(orderStock);
                cartService.deleteCartById(cartRe.getId());
                log.info("Qty cart={}",cartRe.getQty());
            }
        }

        return orderResponse;
    }

    @Override
    public Page<OrderDto> getAllOrdersByCustomer(int page, int size, String status, String orderType) {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        Pageable pageableRequest = PageRequest.of(page,size);
        Page<Order> order = orderRepository.findAllByCustomer(pageableRequest,customer);
        Page<OrderDto> orderDto = order.map(OrderMapper::convertToDTO);
        return orderDto;
    }
}
