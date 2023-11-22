package com.bit.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class TestDto {
    String name;
    MultipartFile multiImage;
}
