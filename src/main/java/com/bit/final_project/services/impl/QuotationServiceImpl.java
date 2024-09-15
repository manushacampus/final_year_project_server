package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.OrderCompleteDto;
import com.bit.final_project.dto.QuotationPriceDto;
import com.bit.final_project.dto.entityDto.*;
import com.bit.final_project.enums.*;
import com.bit.final_project.exceptions.UserNotFoundException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.CustomerMapper;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.models.*;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.repositories.Quotation.QuotationRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
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
    @Autowired
    JobService jobService;
    @Autowired
    PaymentService paymentService;

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
        quotation.setQty(dto.getQty());
        quotation.setType(DESIGN_TYPE.DOOR);
        quotation.setStatus(Status.ACTIVE);
        quotation.setProgress(OrderStatus.PENDING);
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
        quotation.setProgress(OrderStatus.PENDING);
        quotation.setCustomer(customer);
        return quotationRepository.save(quotation);
    }

    @Override
    public Page<QuotationDto> getAllQuotationByStatus(int page, int size, String status, String orderStatus) {
        Pageable pageableRequest = PageRequest.of(page,size);
        if (status == null || status.isEmpty()) {
            // If status is null or empty, return all quotations
            return quotationRepository.findAll(pageableRequest).map(QuotationMapper::convertToDto);
        } else if (orderStatus != null) {
            return quotationRepository.findAllByStatusAndProgress(pageableRequest,Status.valueOf(status), OrderStatus.valueOf(orderStatus)).map(QuotationMapper::convertToDto);
        } else {
            // If status is provided, filter by the given status
            return quotationRepository.findAllByStatus(pageableRequest,Status.valueOf(status)).map(QuotationMapper::convertToDto);
        }
    }

    @Override
    public Quotation changeQuotationType(String id, String type) {
        log.info("status======{}",type);
        Quotation quotation = getQuotationById(id);
        if (type!=null){
            quotation.setProgress(OrderStatus.valueOf(type));
        }
        return quotationRepository.save(quotation);
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
    @Override
    public Quotation approvedQuotation(String id){
        Quotation quotation =getQuotationById(id);
        log.info("quotation Type={}",quotation.getType());

        if (quotation.getType().equals(DESIGN_TYPE.DOOR)){
            jobService.createJobForDoorQuotation(quotation);
        }
        if (quotation.getType().equals(DESIGN_TYPE.WINDOWS)){
            jobService.createJobForWindowQuotation(quotation);
        }
        quotation.setProgress(OrderStatus.PROCESSING);
        return quotationRepository.save(quotation);
    }

    @Override
    public Quotation deliverOrder(String quotationId) {
        Quotation quotation = getQuotationById(quotationId);
        quotation.setProgress(OrderStatus.DELIVER);
        return  quotationRepository.save(quotation);
    }

    @Override
    public Quotation deliveredOrder(String quotationId) {
        Quotation quotation = getQuotationById(quotationId);
        quotation.setProgress(OrderStatus.DELIVERED);
        return quotationRepository.save(quotation);
    }

    @Override
    public Quotation cancelOrder(String quotationId) {
        Quotation quotation = getQuotationById(quotationId);
        quotation.setProgress(OrderStatus.CANCELED);
        return quotationRepository.save(quotation);
    }

    @Override
    public Quotation completeOrder(String orderId, OrderCompleteDto request) throws IOException {
        log.info("Quotation Id--------------={}",request.getPrice());
        Quotation quotation = getQuotationById(orderId);
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setQuotation(QuotationMapper.convertToDto(quotation));
        paymentDto.setPaymentType("COMPLETED");
        paymentDto.setPaymentType("ORDER");
        paymentDto.setPrice(Double.valueOf(request.getPrice()));
        paymentDto.setCustomer(CustomerMapper.convertToDTO(quotation.getCustomer()));
        Payment payment = paymentService.createPayment(paymentDto,request.getInvoice());
        if (payment!=null){
            quotation.setPaymentStatus(PaymentStatus.COMPLETED);
            quotation.setProgress(OrderStatus.COMPLETED);
        }
        return quotationRepository.save(quotation);
    }


}
