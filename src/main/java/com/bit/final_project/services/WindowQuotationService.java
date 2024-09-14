package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.models.DoorQuotation;
import com.bit.final_project.models.WindowQuotation;

public interface WindowQuotationService {
    WindowQuotation getWindowQuotationById(String id);
    WindowQuotation create(WindowQuotationDto windowQuotationDto);
}
