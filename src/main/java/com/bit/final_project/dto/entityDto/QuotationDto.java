package com.bit.final_project.dto.entityDto;

import lombok.Data;

@Data
public class QuotationDto {

    private String id;

    private String type;

    private String status;

    private int qty;

    private String createdAt;

    private String updatedAt;

    private WindowQuotationDto windowQuotation;

    private DoorQuotationDto doorQuotation;

    private CustomerDto customer;
}
