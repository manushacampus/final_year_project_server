package com.bit.final_project.dto;

import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.WindowDto;
import com.bit.final_project.models.Order;
import com.bit.final_project.models.StockItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
public class CustomerOrderStockItemDto {
    private String id;
    private String type;
    private String offer;
    private String status;
    private String createdAt;
    private String updatedAt;
    private int qty;
    private Double price;
    private DoorDto door;
    private WindowDto windows;
    private EmployeeDto employee;
    private JobDto job;

    public static CustomerOrderStockItemDto init(StockItem stockItem, int qty, Order order){
        CustomerOrderStockItemDto stockItemDto = new CustomerOrderStockItemDto();
        stockItemDto.setId(stockItem.getId());
        stockItemDto.setType(String.valueOf(stockItem.getType()));
        stockItemDto.setStatus(String.valueOf(stockItem.getStatus()));
        stockItemDto.setQty(qty);

        if (order.getCreated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(order.getCreated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stockItemDto.setCreatedAt(localDateTime.format(formatter));
        }
        if (order.getUpdated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(order.getCreated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stockItemDto.setUpdatedAt(localDateTime.format(formatter));
        }

        if (stockItem.getDoor() != null) {
            stockItemDto.setDoor(DoorDto.init(stockItem.getDoor()));
        }
        if (stockItem.getWindows() != null) {
            stockItemDto.setWindows(WindowDto.init(stockItem.getWindows()));
        }


        stockItemDto.setPrice(stockItem.getPrice());
        return stockItemDto;
    }
}
