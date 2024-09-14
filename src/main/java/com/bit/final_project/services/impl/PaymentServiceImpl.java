package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.entityDto.PaymentDto;
import com.bit.final_project.enums.PaymentType;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Payment;
import com.bit.final_project.repositories.Order.OrderRepository;
import com.bit.final_project.repositories.Payment.PaymentRepository;
import com.bit.final_project.services.CustomerService;
import com.bit.final_project.services.PaymentService;
import com.bit.final_project.services.QuotationService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    QuotationService quotationService;
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

        if ( paymentDto.getPaymentType() != null) {
            payment.setPaymentType(PaymentType.valueOf(paymentDto.getPaymentType()));
        }

        if ( paymentDto.getOrder() != null && paymentDto.getOrder().getId() != null) {
            payment.setOrder(orderRepository.findById(paymentDto.getOrder().getId()).orElseThrow(() -> new EntityExistsException("Order not found with id: " + paymentDto.getOrder().getId())));
        }
        if (paymentDto.getQuotation() != null && paymentDto.getQuotation().getId() != null) {
            payment.setQuotation(quotationService.getQuotationById(paymentDto.getQuotation().getId()));
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
}
