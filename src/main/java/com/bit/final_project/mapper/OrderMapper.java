package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.*;
import com.bit.final_project.models.Order;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OrderMapper {
    public static OrderDto convertToDTO(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setTotal(order.getTotal());
        dto.setStatus(String.valueOf(order.getStatus()));
        dto.setOrderType(String.valueOf(order.getType()));
        dto.setOrderCode(order.getOrderCode());
        if (order.getCreated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(order.getCreated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dto.setCreatedAt(localDateTime.format(formatter));
        }
        if (order.getUpdated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(order.getUpdated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dto.setUpdatedAt(localDateTime.format(formatter));
        }

        if (order.getFeedback()!=null){
            dto.setFeedback(FeedbackMapper.convertToDTO(order.getFeedback()));
        }
       if (order.getCustomer()!=null){
           dto.setCustomer(CustomerMapper.convertToDTO(order.getCustomer()));
       }

        return dto;
    }
}
