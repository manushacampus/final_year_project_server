package com.bit.final_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OrderCompleteDto {
    private String orderId;
    private String price;
    private MultipartFile invoice;
}
