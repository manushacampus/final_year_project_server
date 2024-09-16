package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.PurchaseDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Inventory;
import com.bit.final_project.models.Purchase;
import com.bit.final_project.models.Supplier;
import com.bit.final_project.repositories.Purchase.PurchaseRepository;
import com.bit.final_project.services.InventoryService;
import com.bit.final_project.services.PurchaseService;
import com.bit.final_project.services.SupplierService;
import com.bit.final_project.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    EmailService emailService;
    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public Purchase getPurchaseById(String id) {
        return purchaseRepository.findById(id).orElseThrow(()-> new EntityExistsException("Purchase not found with id: " + id));
    }

    @Override
    public Purchase createPurchase(int qty, String supplierId, String inventoryId) throws MessagingException {
        Supplier supplier = supplierService.getSupplierbyId(supplierId);
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        Purchase purchase = new Purchase();
        purchase.setId(Generator.getUUID());
        purchase.setQty(qty);
        purchase.setInventory(inventory);
        purchase.setSupplier(supplier);
        purchase.setStatus(Status.ACTIVE);
        Purchase purchaseResponse = purchaseRepository.save(purchase);
        if (purchaseResponse!= null){
            Map<String,Object> templateModel = new HashMap<>();
            templateModel.put("code",inventory.getCode());
            templateModel.put("type",String.valueOf(inventory.getInventoryType()));
            templateModel.put("qty",qty);
            emailService.sendPurchaseRequest(supplier.getEmail(),"Request Item",templateModel);
        }

        return purchaseResponse;
    }

    @Override
    public Page<PurchaseDto> getJPurchaseByStatus(Status status, int page, int size) {

        Pageable pageableRequest = PageRequest.of(page,size);
        return purchaseRepository.findAllByStatus(pageableRequest,status).map(PurchaseDto::init);
    }
}
