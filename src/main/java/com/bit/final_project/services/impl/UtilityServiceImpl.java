package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.entityDto.UtilityDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Utility;
import com.bit.final_project.repositories.Utility.UtilityRepository;
import com.bit.final_project.services.UtilityService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UtilityServiceImpl implements UtilityService {
    @Autowired
    UtilityRepository utilityRepository;
    @Autowired
    FilesStorageService filesStorageService;
    @Override
    public Utility getUtilityById(String id) {
        return utilityRepository.findById(id).orElseThrow(()-> new EntityExistsException("Utility not found with id: " + id));
    }

    @Override
    public Utility save(UtilityDto request, MultipartFile bill) throws IOException {
        Utility utility = new Utility();
        utility.setId(Generator.getUUID());
        utility.setName(request.getName());
        utility.setType(request.getType());
        utility.setBillNo(request.getBillNo());
        utility.setPayment(request.getPayment());
        utility.setStatus(Status.ACTIVE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (request.getDate() != null && !request.getDate().isEmpty()) {
            utility.setDate(LocalDate.parse(request.getDate(), formatter));
        }


        String extension= FilenameUtils.getExtension(bill.getOriginalFilename());
        AppFile appImage = new AppFile(
                "utility",
                Generator.getUUID(),
                extension,
                bill.getInputStream()
        );
        AppFile saveImage=filesStorageService.save(appImage);
        utility.setBill(saveImage.getImageName());

        return utilityRepository.save(utility);
    }

    @Override
    public Page<UtilityDto> getAll(int page, int size, String status) {
        Pageable pageableRequest = PageRequest.of(page,size);
        return utilityRepository.findAllByStatus(pageableRequest,Status.valueOf(status)).map(UtilityDto::init);
    }

    @Override
    public Utility update(String id,UtilityDto request) {
        Utility utility = getUtilityById(id);
        utility.setName(request.getName());
        utility.setBillNo(request.getBillNo());
        utility.setPayment(request.getPayment());
        return utilityRepository.save(utility);
    }
}
