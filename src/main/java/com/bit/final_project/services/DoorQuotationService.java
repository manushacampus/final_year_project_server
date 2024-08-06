package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.models.DoorQuotation;

public interface DoorQuotationService {
    DoorQuotation getDoorQuotationById(String id);
    DoorQuotation create(DoorQuotationDto dto);
}
