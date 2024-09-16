package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.PaymentDto;
import com.bit.final_project.enums.PaymentType;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PaymentService {

    Payment getPaymentById(String id);

    Payment createPayment(PaymentDto paymentDto,MultipartFile invoice) throws IOException;

    Page<PaymentDto> getAllPaymentByStatusAndType(int page, int size, String status, String paymentType,String PaymentStatus);

    List<Payment> getAllPaymentByStatusAndTypeAndDate(Status status, PaymentType type, String date);
}
