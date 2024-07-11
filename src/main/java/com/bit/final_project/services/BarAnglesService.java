package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.BarAnglesDto;
import com.bit.final_project.models.BarAngles;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BarAnglesService {
    BarAngles getBarAnglesBySectionNo(String sectionNo,String code);
    BarAngles addBarAngles(BarAnglesDto request, MultipartFile image) throws IOException;

    Page<BarAngles> getAll(int page, int size,String cat,String barSize,String status);
    List<BarAngles> getAllBarAngels();
}
