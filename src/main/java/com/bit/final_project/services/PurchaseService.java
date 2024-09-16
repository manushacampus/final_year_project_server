package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.PurchaseDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Purchase;
import org.springframework.data.domain.Page;

import javax.mail.MessagingException;

public interface PurchaseService {
    Purchase getPurchaseById(String id);
    Purchase createPurchase(int qty,String supplier,String inventory) throws MessagingException;
    Page<PurchaseDto> getJPurchaseByStatus(Status status, int page, int size);
}
