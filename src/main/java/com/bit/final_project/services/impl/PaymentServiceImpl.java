package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.entityDto.PaymentDto;
import com.bit.final_project.enums.PaymentStatus;
import com.bit.final_project.enums.PaymentType;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.PaymentMapper;
import com.bit.final_project.models.Payment;
import com.bit.final_project.repositories.Order.OrderRepository;
import com.bit.final_project.repositories.Payment.PaymentRepository;
import com.bit.final_project.repositories.Quotation.QuotationRepository;
import com.bit.final_project.services.CustomerService;
import com.bit.final_project.services.PaymentService;
import com.bit.final_project.services.QuotationService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    QuotationRepository  quotationRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    FilesStorageService filesStorageService;

    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityExistsException("Payment not found with id: " + id));
    }

    @Override
    public Payment createPayment(PaymentDto paymentDto, MultipartFile invoice) throws IOException {

        Payment payment = new Payment();
        payment.setId(Generator.getUUID());
        payment.setPrice(paymentDto.getPrice());
        if (invoice==null || invoice.isEmpty()){
            throw new BadRequestException("Invoice Image is empty!");
        }

        if ( paymentDto.getPaymentStatus() != null) {
            payment.setPaymentStatus(PaymentStatus.valueOf(paymentDto.getPaymentStatus()));
        }
        if ( paymentDto.getPaymentType() != null) {
            payment.setType(PaymentType.valueOf(paymentDto.getPaymentType()));
        }

        if ( paymentDto.getOrder() != null && paymentDto.getOrder().getId() != null) {
            payment.setOrder(orderRepository.findById(paymentDto.getOrder().getId()).orElseThrow(() -> new EntityExistsException("Order not found with id: " + paymentDto.getOrder().getId())));
        }
        if (paymentDto.getQuotation() != null && paymentDto.getQuotation().getId() != null) {

            payment.setQuotation(quotationRepository.findById(paymentDto.getQuotation().getId()).orElseThrow(() -> new EntityExistsException("Payment not found with id: " + paymentDto.getQuotation().getId())));
        }
        if (paymentDto.getCustomer() != null && paymentDto.getCustomer().getUser().getId() != null) {
            payment.setCustomer(customerService.getCustomerById(paymentDto.getCustomer().getUser().getId()));
        }

        String extension= FilenameUtils.getExtension(invoice.getOriginalFilename());
        AppFile appImage = new AppFile(
                "payment",
                Generator.getUUID(),
                extension,
                invoice.getInputStream()
        );
        AppFile saveImage=filesStorageService.save(appImage);
        payment.setInvoice(saveImage.getImageName());

        return paymentRepository.save(payment);
    }

    @Override
    public Page<PaymentDto> getAllPaymentByStatusAndType(int page, int size, String status, String paymentType, String statusType) {
        Pageable pageableRequest = PageRequest.of(page,size);
        Page<Payment> payments =paymentRepository.findAllByType(pageableRequest,PaymentType.valueOf(paymentType));
        return payments.map(PaymentMapper::convertToDTO);
    }

    @Override
    public List<Payment> getAllPaymentByStatusAndTypeAndDate(Status status, PaymentType type, String date) {
        LocalDate lDate = LocalDate.parse(date);
        int year = lDate.getYear();
        int month = lDate.getMonthValue();
        return paymentRepository.findByYearAndMonthAndType(year,month,String.valueOf(type));
    }
}
