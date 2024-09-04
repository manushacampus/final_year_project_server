package com.bit.final_project.dto.entityDto;

import lombok.Data;

@Data
public class QuotationDto {

    private String id;

    private String type;

    private String status;

    private WindowQuotationDto windowQuotation;

    private DoorQuotationDto doorQuotation;

    private CustomerDto customer;
}
