package com.bit.final_project.dto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.models.*;
import lombok.Data;

import javax.persistence.*;

@Data
public class StockItemDto {
    private String id;
    private String type;
    private String offer;
    private String status;
    private DoorDto door;
    private WindowDto windows;
    private EmployeeDto employee;
    private JobDto job;

    public static StockItemDto init(StockItem stockItem){
        StockItemDto stockItemDto = new StockItemDto();
        return stockItemDto;
    }

}
