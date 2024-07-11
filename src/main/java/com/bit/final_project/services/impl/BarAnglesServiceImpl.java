package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.URL;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.entityDto.BarAnglesDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.BarAngles;
import com.bit.final_project.repositories.BarAngles.BarAnglesRepository;
import com.bit.final_project.services.BarAnglesService;
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
public class BarAnglesServiceImpl implements BarAnglesService {
    @Autowired
    BarAnglesRepository barAnglesRepository;
    @Autowired
    FilesStorageService filesStorageService;

    @Override
    public BarAngles getBarAnglesBySectionNo(String sectionNo,String code) {
        return barAnglesRepository.findBySectionNoOrCode(sectionNo,code).orElseThrow(()-> new EntityExistsException("Bar Angels not found with sectionNo or code: " +sectionNo+" "+code));
    }

    @Override
    public BarAngles addBarAngles(BarAnglesDto request, MultipartFile image) throws IOException {
        if (barAnglesRepository.findBySectionNoOrCode(request.getSectionNo(),request.getCode()).isPresent()){
            throw new EntityExistsException("existing Section code");
        }
        if (image==null || image.isEmpty()){
            throw new BadRequestException("picture is empty!");
        }
        String extension= FilenameUtils.getExtension(image.getOriginalFilename());
        AppFile AppImage = new AppFile(
                "sections",
                Generator.getUUID(),
                extension,
                image.getInputStream()
        );
        AppFile saveImage=filesStorageService.save(AppImage);
        BarAngles barAngles = new BarAngles();
        barAngles.setId(Generator.getUUID());
        barAngles.setCode(request.getCode());
        barAngles.setCategory(request.getCategory());
        barAngles.setSectionNo(request.getSectionNo());
        barAngles.setStatus(Status.ACTIVE);
        barAngles.setName(request.getName());
        barAngles.setThickness(request.getThickness());
        barAngles.setWeight(request.getWeight());
        barAngles.setSize(request.getSize());
        barAngles.setImage(saveImage.getImageName());
        return barAnglesRepository.save(barAngles);
    }

    @Override
    public Page<BarAngles> getAll(int page, int size, String cat, String barSize, String status) {
        log.info("page={}",page);
        log.info("size={}",size);
        Pageable pageableRequest = PageRequest.of(page,size);
        if ((cat==null || cat == "") && (barSize==null || barSize == "")){
            log.info("cat and bar size null");
            Page<BarAngles> sectionPage =barAnglesRepository.findByStatus(pageableRequest,Status.ACTIVE);
            sectionPage.getContent().forEach(sectionPageItem ->{
                if (sectionPageItem.getImage() != null) {
                    sectionPageItem.setImage(URL.fileStorageUrl.replace("{type}", "sections").replace("{fileName}", sectionPageItem.getImage()));
                }
            });
            return sectionPage;
        }
        if (cat==null || cat == ""){
            log.info("cat null");
            Page<BarAngles> sectionPage =barAnglesRepository.findBySizeAndStatus(pageableRequest,barSize, Status.ACTIVE);
            sectionPage.getContent().forEach(sectionPageItem ->{
                if (sectionPageItem.getImage() != null) {
                    sectionPageItem.setImage(URL.fileStorageUrl.replace("{type}", "sections").replace("{fileName}", sectionPageItem.getImage()));
                }
            });
            return sectionPage;
        }
        if (barSize==null || barSize == ""){
            log.info("bar size null");
            Page<BarAngles> sectionPage = barAnglesRepository.findByCategoryAndStatus(pageableRequest,cat, Status.ACTIVE);
            sectionPage.getContent().forEach(sectionPageItem ->{
                if (sectionPageItem.getImage() != null) {
                    sectionPageItem.setImage(URL.fileStorageUrl.replace("{type}", "sections").replace("{fileName}", sectionPageItem.getImage()));
                }
            });
            return sectionPage;
        }
        log.info("not null");
        Page<BarAngles> sectionPage = barAnglesRepository.findByCategoryAndSizeAndStatus(pageableRequest,cat,barSize, Status.ACTIVE);
        sectionPage.getContent().forEach(sectionPageItem ->{
            if (sectionPageItem.getImage() != null) {
                sectionPageItem.setImage(URL.fileStorageUrl.replace("{type}", "sections").replace("{fileName}", sectionPageItem.getImage()));
            }
        });
        return sectionPage;
    }

    @Override
    public List<BarAngles> getAllBarAngels() {
        return barAnglesRepository.findAll();
    }
}
