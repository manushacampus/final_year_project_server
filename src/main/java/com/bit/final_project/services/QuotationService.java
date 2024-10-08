package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.models.Quotation;

import java.util.List;

public interface QuotationService {
    Quotation createDoorQuotation(DoorQuotationDto dto);
    Quotation createWindowQuotation(WindowQuotationDto dto);
    List<Quotation> getAllQuotationByStatus(String status);

    Quotation changeQuotationType(String id,String type);
}
