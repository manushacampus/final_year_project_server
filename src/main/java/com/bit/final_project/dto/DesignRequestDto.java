package com.bit.final_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DesignRequestDto {
    private String designDto;
    private MultipartFile image;
}
