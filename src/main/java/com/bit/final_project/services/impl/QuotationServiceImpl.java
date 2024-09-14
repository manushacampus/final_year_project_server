package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.QuotationPriceDto;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.enums.DESIGN_TYPE;
import com.bit.final_project.enums.HeightOrWidth;
import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.UserNotFoundException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Customer;
import com.bit.final_project.models.Design;
import com.bit.final_project.models.DesignInventory;
import com.bit.final_project.models.Quotation;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.repositories.Quotation.QuotationRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.DesignService;
import com.bit.final_project.services.DoorQuotationService;
import com.bit.final_project.services.QuotationService;
import com.bit.final_project.services.WindowQuotationService;
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
    WindowQuotationService windowQuotationService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DesignService designService;

    @Override
    public Quotation getQuotationById(String id) {
        return quotationRepository.findById(id).orElseThrow(()-> new EntityExistsException("Quotation not found with id: " + id));

    }

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
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        log.info("customer --------={}",customer.getUser_id());
        if (customer == null) {
            throw new EntityNotFoundException("Cannot place order: user not found");
        }
        Quotation quotation = new Quotation();
        quotation.setId(Generator.getUUID());
        quotation.setWindowQuotation(windowQuotationService.create(dto));
        quotation.setQty(dto.getQty());
        quotation.setType(DESIGN_TYPE.WINDOWS);
        quotation.setStatus(Status.ACTIVE);
        quotation.setCustomer(customer);
        return quotationRepository.save(quotation);
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

    @Override
    public Quotation changeQuotationType(String id, String type) {
        return null;
    }

    @Override
    public QuotationPriceDto calQuotation(DoorQuotationDto dto) {
        Double total=0.0;
        Double height = dto.getHeight();
        Double width = dto.getWidth();
        Design design = designService.getDesignById(dto.getDesign().getId());
        List<DesignInventory> designInventory = designService.getInventoryByDesign(design.getId());
        for (DesignInventory inventory : designInventory) {
            System.out.println(inventory);

            if (inventory.getInventory().getInventoryType().equals(INVENTORY_TYPE.OTHER)){
                if (inventory.getQty()>0){
                    total = total +(inventory.getInventory().getPrice())*inventory.getQty();
                }else {
                    total = total +(inventory.getInventory().getPrice());
                }

            }
            if (inventory.getInventory().getInventoryType().equals(INVENTORY_TYPE.BAR)){
                Double length = inventory.getInventory().getBar().getLength();
                if (inventory.getType().equals(HeightOrWidth.HEIGHT)){
                    if (height > 0 && height <= (length*1/4)) {
                        total = total +inventory.getInventory().getPrice() * 1 / 4;
                    } else if (height > (length*1/4) && height <= (length*2/4)) {
                        total = total + inventory.getInventory().getPrice() * 2 / 4;
                    }
                    else if (height > (length*2/4) && height <= (length*3/4)) {
                        total = total + inventory.getInventory().getPrice() * 3 / 4;
                    }
                    else if (height > (length*3/4) && height <= (length*4/4)) {
                        total = total + inventory.getInventory().getPrice() * 4 / 4;
                    }
                }
                else if (inventory.getType().equals(HeightOrWidth.WIDTH)){
                    if (width > 0 && width <= (length*1/4)) {
                        total = total + inventory.getInventory().getPrice() * 1 / 4;
                    } else if (width > (length*1/4) && width <= (length*2/4)) {
                        total = total + inventory.getInventory().getPrice() * 2 / 4;
                    }
                    else if (width > (length*2/4) && width <= (length*3/4)) {
                        total = total + inventory.getInventory().getPrice() * 3 / 4;
                    }
                    else if (width > (length*3/4) && width <= (length*4/4)) {
                        total = total + inventory.getInventory().getPrice() * 4 / 4;
                    }
                }

                total = total +inventory.getInventory().getPrice();
            }
            else if (inventory.getInventory().getInventoryType().equals(INVENTORY_TYPE.BOARD)){
                total = total +(inventory.getInventory().getPrice())*((width*height)/92903.04);
            }
        }
        QuotationPriceDto quotationPriceDto = new QuotationPriceDto();
        double roundedTotal = Math.round(total * 100.0) / 100.0;
        quotationPriceDto.setPrice(roundedTotal);
        return quotationPriceDto;
    }

    @Override
    public List<Quotation> getAllByUser() {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        return quotationRepository.findAllByCustomer(customer);
    }


}
