package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.Other;
import lombok.Data;


@Data
public class OtherDto {
    private String id;
    private String name;
    private String type;

    public static OtherDto init(Other other){
        OtherDto otherDto = new OtherDto();
        otherDto.setId(otherDto.id);
        otherDto.setName(other.getName());
        otherDto.setType(other.getType());
        return otherDto;
    }
}
