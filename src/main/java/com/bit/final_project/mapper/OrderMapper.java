package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.*;
import com.bit.final_project.models.Order;

public class OrderMapper {
    public static OrderDto convertToDTO(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setTotal(order.getTotal());
        if (order.getFeedback()!=null){
            dto.setFeedback(FeedbackMapper.convertToDTO(order.getFeedback()));
        }
       if (order.getCustomer()!=null){
           dto.setCustomer(CustomerMapper.convertToDTO(order.getCustomer()));
       }

        return dto;
    }
}
