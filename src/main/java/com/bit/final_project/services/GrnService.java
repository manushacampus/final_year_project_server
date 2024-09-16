package com.bit.final_project.services;

import com.bit.final_project.dto.RequestGrnDto;
import com.bit.final_project.models.Grn;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GrnService {
    Grn getGrnById(String id);

    Grn createGrn(RequestGrnDto requestGrnDto, String purchaseId, MultipartFile invoice) throws IOException;
    List<Grn> getGrnByPurchase(String purchaseId);

    List<Grn> getGrnByDate(int year,int month);
}
