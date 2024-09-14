package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.OrderStockDto;
import com.bit.final_project.dto.entityDto.OrderDto;
import com.bit.final_project.dto.entityDto.StockItemDto;
import com.bit.final_project.enums.OrderStatus;
import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.mapper.StockItemMapper;
import com.bit.final_project.models.*;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.repositories.Order.OrderRepository;
import com.bit.final_project.repositories.OrderStock.OrderStockRepository;
import com.bit.final_project.repositories.StockItem.StockItemRepository;
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

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
    StockItemRepository stockItemRepository;
    @Autowired
    CartService cartService;
    @Autowired
    StockService stockService;

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityExistsException("Order not found with id: " + id));
    }

    @Override
    public Order orderProduct(String id,int qty) {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        StockItem stockItem = stockService.getStockItemById(id);
        Order order = new Order();
        order.setId(Generator.getUUID());
        order.setCustomer(customer);
        order.setTotal(stockItem.getPrice());
        order.setStatus(Status.ACTIVE);
        order.setType(OrderStatus.PENDING);
        Order orderResponse = orderRepository.save(order);

        OrderStock orderStock = new OrderStock();
        orderStock.setId(Generator.getUUID());
        orderStock.setQty(qty);
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
                orderStock.setQty(cartRe.getQty());
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

    @Override
    public Page<OrderDto> getAllOrders(int page, int size, String status, String orderType) {
        Pageable pageableRequest = PageRequest.of(page,size);
        Page<Order> order = orderRepository.findAllByStatusAndType(pageableRequest,Status.valueOf(status), OrderStatus.valueOf(orderType));
        Page<OrderDto> orderDto = order.map(OrderMapper::convertToDTO);
        return orderDto;

    }


    @Override
    public OrderStockDto getOrderStockById(String id) {
        Order order = getOrderById(id);
       List<OrderStock>  orderStockList =orderStockRepository.findAllByOrder(order);
       OrderStockDto orderStockDto = new OrderStockDto();
       orderStockDto.setOrder(OrderMapper.convertToDTO(order));
       orderStockDto.setStockItem(orderStockList.stream().map(StockItemMapper::convertToDtoByOrderStock).collect(Collectors.toList()));
       return orderStockDto;

    }

    @Override
    public Order acceptOrder(String orderId) {
        Order order = getOrderById(orderId);
        List<OrderStock>  orderStockList =orderStockRepository.findAllByOrder(order);

        if (orderStockList == null || orderStockList.isEmpty()) {
            throw new EntityNotFoundException("No order stock found for the given order.");
        }
        else {
            for (OrderStock orderStock : orderStockList) {
                int stockQty = orderStock.getStockItem().getQty();
                int requestQty = orderStock.getQty();
                String code="0000";
                if (orderStock.getStockItem().getType().equals(PRODUCT_TYPE.DOOR)){
                    code  = orderStock.getStockItem().getDoor().getCode();
                }
                if (orderStock.getStockItem().getType().equals(PRODUCT_TYPE.WINDOWS)){
                    code  = orderStock.getStockItem().getWindows().getCode();
                }

                if (stockQty >= requestQty) {
                    StockItem stockItem = stockService.getStockItemById(orderStock.getStockItem().getId());
                    stockItem.setQty(stockItem.getQty()-requestQty);
                    stockItemRepository.save(stockItem);
                }
                else {
                    throw new EntityNotFoundException("Out Of Stock Code : "+code);
                }

            }
            order.setType(OrderStatus.APPROVED);
            return orderRepository.save(order);
        }
    }
}
