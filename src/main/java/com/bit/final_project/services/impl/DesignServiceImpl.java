package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.DesignAndInventoryDto;
import com.bit.final_project.dto.entityDto.DesignDto;
import com.bit.final_project.enums.DESIGN_TYPE;
import com.bit.final_project.enums.HeightOrWidth;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.DesignMapper;
import com.bit.final_project.models.Design;
import com.bit.final_project.models.DesignInventory;
import com.bit.final_project.models.Inventory;
import com.bit.final_project.repositories.Design.DesignRepository;
import com.bit.final_project.repositories.DesignInventory.DesignInventoryRepository;
import com.bit.final_project.services.DesignService;
import com.bit.final_project.services.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class DesignServiceImpl implements DesignService {
    @Autowired
    DesignRepository designRepository;
    @Autowired
    DesignInventoryRepository designInventoryRepository;
    @Autowired
    FilesStorageService filesStorageService;
    @Autowired
    InventoryService inventoryService;

    @Override
    public Design getDesignById(String id) {
        return designRepository.findById(id).orElseThrow(() -> new EntityExistsException("Design not found with id: " + id));
    }

    @Override
    public DesignInventory getDesignInventoryById(String id) {
        return designInventoryRepository.findById(id).orElseThrow(() -> new EntityExistsException("DesignInventory not found with id: " + id));
    }

    @Override
    public Design create(DesignDto designDto, MultipartFile image) throws IOException {
        if (image==null || image.isEmpty()){
            throw new BadRequestException("Product Design Image is empty!");
        }
        String extension= FilenameUtils.getExtension(image.getOriginalFilename());
        AppFile appImage = new AppFile(
                "product-design",
                Generator.getUUID(),
                extension,
                image.getInputStream()
        );
        AppFile saveProductDesignImage=filesStorageService.save(appImage);
        Design design = Design.init(designDto);
        design.setId(Generator.getUUID());
        design.setImage(saveProductDesignImage.getImageName());
        design.setStatus(Status.INACTIVE);
        return designRepository.save(design);
    }

    @Override
    public Inventory addInventoryForDesign(DesignAndInventoryDto request) {
        Design design = getDesignById(request.getDesignId());
        Inventory inventory = inventoryService.getInventoryById(request.getInventoryId());
        DesignInventory designInventory = new DesignInventory();
        designInventory.setId(Generator.getUUID());
        if (!request.getType().isEmpty() || request.getType()!=""){
            designInventory.setType(HeightOrWidth.valueOf(request.getType()));
        }
        designInventory.setQty(request.getQty());
        designInventory.setDesign(design);
        designInventory.setInventory(inventory);
        return designInventoryRepository.save(designInventory).getInventory();
    }

    @Override
    public Page<DesignDto> getALlDesignByStatusPage(String status, int page, int size,String type) {
        Pageable pageableRequest = PageRequest.of(page,size);
        if (status!=""){
            if (type!=""){
                return  designRepository.findAllByStatusAndType(pageableRequest,Status.valueOf(status),DESIGN_TYPE.valueOf(type)).map(DesignMapper::convertToDTO);
            }
            return  designRepository.findAllByStatus(pageableRequest,Status.valueOf(status)).map(DesignMapper::convertToDTO);

        }
        return  designRepository.findAll(pageableRequest).map(DesignMapper::convertToDTO);
    }

    @Override
    public List<DesignInventory> getInventoryByDesign(String designId) {

        return designInventoryRepository.findAllByDesign(getDesignById(designId));
    }
    @Override
    public DesignInventory deleteById(String id) {
       DesignInventory designInventory = getDesignInventoryById(id);
        designInventoryRepository.deleteById(id);
        return designInventory;
    }

    @Override
    public Design Update(DesignDto request) {
        Design design = getDesignById(request.getId());
        if (request.getCode()!=null){
            design.setCode(request.getCode());
        }
        if (request.getType()!=null){
            design.setType(DESIGN_TYPE.valueOf(request.getType()));
        }
        if (request.getName()!=null){
            design.setName(request.getName());
        }
        return designRepository.save(design);


    }

    @Override
    public Design changeStatus(String status,String id) {
        Design design = getDesignById(id);
        design.setStatus(Status.valueOf(status));
        return designRepository.save(design);
    }
}
