package com.bit.final_project.services;

import com.bit.final_project.dto.DesignAndInventoryDto;
import com.bit.final_project.dto.entityDto.DesignDto;
import com.bit.final_project.models.Design;
import com.bit.final_project.models.DesignInventory;
import com.bit.final_project.models.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DesignService {
     Design getDesignById(String id);
     DesignInventory getDesignInventoryById(String id);
     Design create(DesignDto designDto, MultipartFile image) throws IOException;

     Inventory addInventoryForDesign(DesignAndInventoryDto request);

     Page<DesignDto> getALlDesignByStatusPage(String status,int page, int size);

     List<DesignInventory> getInventoryByDesign(String designId);

     Design changeStatus(String status,String id);

     DesignInventory deleteById(String id);
}
