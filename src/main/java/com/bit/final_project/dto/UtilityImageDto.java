package com.bit.final_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UtilityImageDto {
    private String utilityDto;
    private MultipartFile bill;
}
