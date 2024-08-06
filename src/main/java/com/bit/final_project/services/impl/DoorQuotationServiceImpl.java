package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.models.DoorQuotation;
import com.bit.final_project.repositories.DoorQuotation.DoorQuotationRepository;
import com.bit.final_project.services.DesignService;
import com.bit.final_project.services.DoorQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DoorQuotationServiceImpl implements DoorQuotationService {
    @Autowired
    DoorQuotationRepository doorQuotationRepository;
    @Autowired
    DesignService designService;


    @Override
    public DoorQuotation getDoorQuotationById(String id) {
        return doorQuotationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("DoorQuotation with ID " + id + " not found."));
    }

    @Override
    public DoorQuotation create(DoorQuotationDto dto) {
        DoorQuotation doorQuotation = new DoorQuotation();
        doorQuotation.setId(Generator.getUUID());
        doorQuotation.setHeight(dto.getHeight());
        doorQuotation.setWidth(dto.getWidth());
        doorQuotation.setColor(dto.getColor());
        doorQuotation.setDesign(designService.getDesignById(dto.getDesign().getId()));
        return doorQuotationRepository.save(doorQuotation);
    }
}
