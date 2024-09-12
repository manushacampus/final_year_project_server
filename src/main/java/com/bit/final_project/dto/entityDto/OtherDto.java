package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.Other;
import lombok.Data;


@Data
public class OtherDto {
    private String id;
    private String name;
    private String type;
    private String colorType;
    private String color;
    private Double length;
    private Double  weight;

    public static OtherDto init(Other other){
        OtherDto otherDto = new OtherDto();
        otherDto.setId(otherDto.id);
        otherDto.setColor(otherDto.getColor());
        otherDto.setLength(other.getLength());
        otherDto.setWeight(other.getWeight());
        otherDto.setName(other.getName());
        otherDto.setType(other.getType());
        return otherDto;
    }
}
