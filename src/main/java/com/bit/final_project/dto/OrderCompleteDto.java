package com.bit.final_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OrderCompleteDto {
    private String orderId;
    private Double price;
    private MultipartFile invoice;
}
