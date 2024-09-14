package com.bit.final_project.dto.entityDto;
import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.models.*;
import lombok.Data;

@Data
public class StockItemDto {
    private String id;
    private String type;
    private String offer;
    private String status;
    private Double price;
    private DoorDto door;
    private WindowDto windows;
    private EmployeeDto employee;
    private JobDto job;

    public static StockItemDto init(StockItem stockItem){
        StockItemDto stockItemDto = new StockItemDto();
        stockItemDto.setId(stockItem.getId());
        stockItemDto.setType(String.valueOf(stockItem.getType()));
        stockItemDto.setStatus(String.valueOf(stockItem.getStatus()));
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
