package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.models.DoorQuotation;
import com.bit.final_project.models.WindowQuotation;
import com.bit.final_project.repositories.WindowQuotation.WindowQuotationRepository;
import com.bit.final_project.services.DesignService;
import com.bit.final_project.services.WindowQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class WindowQuotationServiceImpl implements WindowQuotationService {
    @Autowired
    WindowQuotationRepository windowQuotationRepository;
    @Autowired
    DesignService designService;
    @Override
    public WindowQuotation getWindowQuotationById(String id) {

        return windowQuotationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Window Quotation with ID " + id + " not found."));
    }

    @Override
    public WindowQuotation create(WindowQuotationDto windowQuotationDto) {
        WindowQuotation windowQuotation = new WindowQuotation();
        windowQuotation.setId(Generator.getUUID());
        windowQuotation.setHeight(windowQuotationDto.getHeight());
        windowQuotation.setWidth(windowQuotationDto.getWidth());
        windowQuotation.setColor(windowQuotationDto.getColor());
        windowQuotation.setDesign(designService.getDesignById(windowQuotationDto.getDesign().getId()));
        return windowQuotationRepository.save(windowQuotation);
    }
}
