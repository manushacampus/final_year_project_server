package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.enums.DESIGN_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.UserNotFoundException;
import com.bit.final_project.models.Customer;
import com.bit.final_project.models.Quotation;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.repositories.Quotation.QuotationRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.DoorQuotationService;
import com.bit.final_project.services.QuotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class QuotationServiceImpl implements QuotationService {
    @Autowired
    QuotationRepository quotationRepository;
    @Autowired
    DoorQuotationService doorQuotationService;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Quotation createDoorQuotation(DoorQuotationDto dto) {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        log.info("customer --------={}",customer.getUser_id());
        if (customer == null) {
            throw new EntityNotFoundException("Cannot place order: user not found");
        }
        Quotation quotation = new Quotation();
        quotation.setId(Generator.getUUID());
        quotation.setDoorQuotation(doorQuotationService.create(dto));
        quotation.setType(DESIGN_TYPE.DOOR);
        quotation.setStatus(Status.ACTIVE);
        quotation.setCustomer(customer);
        return quotationRepository.save(quotation);
    }

    @Override
    public Quotation createWindowQuotation(WindowQuotationDto dto) {
        return null;
    }

    @Override
    public List<Quotation> getAllQuotationByStatus(String status) {

        if (status == null || status.isEmpty()) {
            // If status is null or empty, return all quotations
            return quotationRepository.findAll();
        } else {
            // If status is provided, filter by the given status
            return quotationRepository.findAllByStatus(Status.valueOf(status));
        }
    }
}
