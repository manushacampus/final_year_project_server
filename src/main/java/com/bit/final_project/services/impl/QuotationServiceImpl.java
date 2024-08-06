package com.bit.final_project.services.impl;

import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.enums.DESIGN_TYPE;
import com.bit.final_project.models.Quotation;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.repositories.Quotation.QuotationRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.DoorQuotationService;
import com.bit.final_project.services.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationServiceImpl implements QuotationService {
    @Autowired
    QuotationRepository quotationRepository;
    @Autowired
    DoorQuotationService doorQuotationService;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Quotation createDoorQuotation(DoorQuotationDto dto) {
        Quotation quotation = new Quotation();
        quotation.setDoorQuotation(doorQuotationService.create(dto));
        quotation.setType(DESIGN_TYPE.DOOR);
        quotation.setCustomer(customerRepository.findByUser(CurrentUser.getUser()));
        return quotationRepository.save(quotation);
    }

    @Override
    public Quotation createWindowQuotation(WindowQuotationDto dto) {
        return null;
    }
}
