package com.bit.final_project.mapper;

import com.bit.final_project.commons.URL;
import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.dto.entityDto.DesignDto;
import com.bit.final_project.models.Design;
import com.bit.final_project.models.Employee;

public class DesignMapper {
    public static DesignDto convertToDTO(Design design) {
        DesignDto dto = new DesignDto();
        dto.setId(design.getId());
        dto.setCode(design.getCode());
        dto.setType(String.valueOf(design.getType()));
        dto.setStatus(String.valueOf(design.getStatus()));
        dto.setName(design.getName());
        dto.setImage(URL.fileStorageUrl.replace("{type}","product-design").replace("{fileName}",design.getImage()));
        return dto;
    }
}
