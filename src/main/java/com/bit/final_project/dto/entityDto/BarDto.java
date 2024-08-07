package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.Bar;
import lombok.Data;

@Data
public class BarDto {
    private String id;
    private String color;
    private String length;
    private BarAnglesDto barAngles;

    public static BarDto init(Bar bar){
        BarDto barDto = new BarDto();
        barDto.setId(bar.getId());
        barDto.setColor(bar.getColor());
        barDto.setLength(bar.getLength());
        barDto.setBarAngles(BarAnglesDto.init(bar.getBarAngles()));
        return barDto;
    }
}
