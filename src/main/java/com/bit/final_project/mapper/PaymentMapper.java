package com.bit.final_project.mapper;

import com.bit.final_project.commons.URL;
import com.bit.final_project.dto.entityDto.OrderDto;
import com.bit.final_project.dto.entityDto.PaymentDto;
import com.bit.final_project.models.Order;
import com.bit.final_project.models.Payment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PaymentMapper {

    public static PaymentDto convertToDTO(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setPrice(payment.getPrice());
        dto.setPaymentType(String.valueOf(payment.getType()));
        dto.setPaymentStatus(String.valueOf(payment.getPaymentStatus()));
        if (payment.getInvoice()!=null) {
            dto.setInvoice(URL.fileStorageUrl.replace("{type}", "payment").replace("{fileName}", payment.getInvoice()));
        }
        if (payment.getCreated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(payment.getCreated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dto.setCreatedAt(localDateTime.format(formatter));
        }
        if (payment.getUpdated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(payment.getUpdated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dto.setUpdatedAt(localDateTime.format(formatter));
        }

        if (payment.getOrder()!=null){
            dto.setOrder(OrderMapper.convertToDTO(payment.getOrder()));
        }
        if (payment.getCustomer()!=null){
            dto.setCustomer(CustomerMapper.convertToDTO(payment.getCustomer()));
        }
        if (payment.getQuotation()!=null){
            dto.setQuotation(QuotationMapper.convertToDto(payment.getQuotation()));
        }

        return dto;
    }
}
