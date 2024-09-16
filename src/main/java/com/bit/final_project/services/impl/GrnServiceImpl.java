package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.RequestGrnDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Grn;
import com.bit.final_project.models.Inventory;
import com.bit.final_project.models.Purchase;
import com.bit.final_project.repositories.Inventory.InventoryRepository;
import com.bit.final_project.repositories.Purchase.PurchaseRepository;
import com.bit.final_project.repositories.grn.GrnRepository;
import com.bit.final_project.services.GrnService;
import com.bit.final_project.services.InventoryService;
import com.bit.final_project.services.PurchaseService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GrnServiceImpl implements GrnService {
    @Autowired
    GrnRepository grnRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    FilesStorageService filesStorageService;

    @Override
    public Grn getGrnById(String id) {
        return grnRepository.findById(id).orElseThrow(()-> new EntityExistsException("GRN not found with id: " + id));
    }

    @Override
    @Transactional
    public Grn createGrn(RequestGrnDto requestGrnDto, String purchaseId, MultipartFile invoice) throws IOException {
        if (requestGrnDto.getQty()<=0) {
            throw new EntityNotFoundException("Enter Valid Qty");
        }
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        Inventory inventory = purchase.getInventory();
        int avQty=purchase.getQty()-purchase.getDispatchQty();
        Grn grn = new Grn();
        grn.setId(Generator.getUUID());
        grn.setQty(requestGrnDto.getQty());
        grn.setInvoiceNo(requestGrnDto.getInvoiceNo());
        grn.setPrice(requestGrnDto.getPayment());
        grn.setPurchase(purchase);
        grn.setStatus(Status.ACTIVE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (requestGrnDto.getDate() != null && !requestGrnDto.getDate().isEmpty()) {
            grn.setDate(LocalDate.parse(requestGrnDto.getDate(), formatter));
        }

        if (purchase.getQty()>=requestGrnDto.getQty()){
            if (avQty>=requestGrnDto.getQty()){

                String extension= FilenameUtils.getExtension(invoice.getOriginalFilename());
                AppFile appImage = new AppFile(
                        "grn",
                        Generator.getUUID(),
                        extension,
                        invoice.getInputStream()
                );
                AppFile saveImage=filesStorageService.save(appImage);
                grn.setInvoice(saveImage.getImageName());


                purchase.setDispatchQty(purchase.getDispatchQty()+ requestGrnDto.getQty());
                inventory.setQty(inventory.getQty()+ requestGrnDto.getQty());
                Grn response = grnRepository.save(grn);
                inventoryRepository.save(inventory);
                purchaseRepository.save(purchase);
                return response;
            }
            else {
                throw new EntityNotFoundException("Requested quantity exceeds");
            }
        }
        else{
            throw new EntityNotFoundException("Requested quantity exceeds");
        }
    }

    @Override
    public List<Grn> getGrnByPurchase(String purchaseId) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        return grnRepository.findAllByPurchase(purchase);
    }

    @Override
    public List<Grn> getGrnByDate(int year, int month) {
        return grnRepository.findAllByDate(Status.ACTIVE,year,month);

    }
}
