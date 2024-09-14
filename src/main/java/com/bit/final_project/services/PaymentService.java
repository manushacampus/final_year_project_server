package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.PaymentDto;
import com.bit.final_project.models.Payment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PaymentService {

    Payment getPaymentById(String id);

    Payment createPayment(PaymentDto paymentDto,MultipartFile invoice) throws IOException;
}