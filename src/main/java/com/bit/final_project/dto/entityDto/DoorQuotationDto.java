package com.bit.final_project.dto.entityDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoorQuotationDto {
    private String id;
    private Long height;
    private Long width;
    private String color;
    private DesignDto design;
}
