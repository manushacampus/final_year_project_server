package com.bit.final_project.mapper;

import com.bit.final_project.dto.CustomerOrderStockItemDto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.QuotationDto;
import com.bit.final_project.dto.entityDto.StockItemDto;
import com.bit.final_project.dto.entityDto.WindowDto;
import com.bit.final_project.models.OrderStock;
import com.bit.final_project.models.Quotation;
import com.bit.final_project.models.StockItem;

public class StockItemMapper {
    public static StockItemDto convertToDto(StockItem stockItem){
        StockItemDto stockItemDto = new StockItemDto();
        stockItemDto.setId(stockItem.getId());
        stockItemDto.setType(String.valueOf(stockItem.getType()));
        stockItemDto.setStatus(String.valueOf(stockItem.getStatus()));
        stockItemDto.setQty(stockItem.getQty());
        if (stockItem.getDoor() != null) {
            stockItemDto.setDoor(DoorDto.init(stockItem.getDoor()));
        }
        if (stockItem.getWindows() != null) {
            stockItemDto.setWindows(WindowDto.init(stockItem.getWindows()));
        }


        stockItemDto.setPrice(stockItem.getPrice());
        return stockItemDto;
    }
    public static CustomerOrderStockItemDto convertToDtoByOrderStock(OrderStock orderStock){
        CustomerOrderStockItemDto stockItemDto = new CustomerOrderStockItemDto();
        stockItemDto.setId(orderStock.getStockItem().getId());
        stockItemDto.setType(String.valueOf(orderStock.getStockItem().getType()));
        stockItemDto.setStatus(String.valueOf(orderStock.getStockItem().getStatus()));
        stockItemDto.setQty(orderStock.getQty());
        if (orderStock.getStockItem().getDoor() != null) {
            stockItemDto.setDoor(DoorDto.init(orderStock.getStockItem().getDoor()));
        }
        if (orderStock.getStockItem().getWindows() != null) {
            stockItemDto.setWindows(WindowDto.init(orderStock.getStockItem().getWindows()));
        }


        stockItemDto.setPrice(orderStock.getStockItem().getPrice());
        return stockItemDto;
    }
}
