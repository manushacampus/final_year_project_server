package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.UtilityDto;
import com.bit.final_project.models.Utility;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UtilityService {
    Utility getUtilityById(String id);
    Utility save(UtilityDto request, MultipartFile bill) throws IOException;

    Page<UtilityDto> getAll(int page, int size,String status);
    Utility update(String id,UtilityDto request);

    List<Utility> getUtilityByDate(int year, int month);
}
