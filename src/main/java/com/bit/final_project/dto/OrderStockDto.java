package com.bit.final_project.dto;

import com.bit.final_project.dto.entityDto.OrderDto;
import com.bit.final_project.dto.entityDto.StockItemDto;
import lombok.Data;

import java.util.List;
@Data
public class OrderStockDto {
    private OrderDto order;
    private List<CustomerOrderStockItemDto> stockItem;

}
