package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.BarAngles;
import lombok.Data;

@Data
public class BarAnglesDto {
    private String id;
    private String sectionNo;
    private String name;
    private String code;
    private String thickness;
    private String weight;
    private String category;
    private String size;
    private String image;
    private String status;

    public static BarAnglesDto init(BarAngles request){
        BarAnglesDto barAnglesDto = new BarAnglesDto();
        barAnglesDto.setId(request.getId());
        barAnglesDto.setSectionNo(request.getSectionNo());
        barAnglesDto.setName(request.getName());
        barAnglesDto.setCode(request.getCode());
        barAnglesDto.setThickness(request.getThickness());
        barAnglesDto.setWeight(request.getWeight());
        barAnglesDto.setCategory(request.getCategory());
        barAnglesDto.setSize(request.getSize());
        barAnglesDto.setStatus(String.valueOf(request.getStatus()));
        return barAnglesDto;
    }


}
