package com.bit.final_project.dto.entityDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowQuotationDto {
    private String id;
    private Double height;
    private Double width;
    private int qty;
    private String color;
    private DesignDto design;
}
