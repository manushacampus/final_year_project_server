package com.bit.final_project.mapper;


import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.models.DoorQuotation;

public class DoorQuotationMapper {
    public static DoorQuotationDto convertToDTO(DoorQuotation doorQuotation) {
        DoorQuotationDto doorQuotationDto = new DoorQuotationDto();
        doorQuotationDto.setId(doorQuotation.getId());
        doorQuotationDto.setWidth(doorQuotation.getWidth());
        doorQuotationDto.setHeight(doorQuotation.getHeight());
        doorQuotationDto.setColor(doorQuotation.getColor());
        doorQuotationDto.setDesign(DesignMapper.convertToDTO(doorQuotation.getDesign()));
        return doorQuotationDto;
    }

}
