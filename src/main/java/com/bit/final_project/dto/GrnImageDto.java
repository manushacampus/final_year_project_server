package com.bit.final_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class GrnImageDto {
    private MultipartFile invoice;
    private String grnDto;
}
