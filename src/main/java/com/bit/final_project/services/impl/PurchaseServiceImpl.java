package com.bit.final_project.services.impl;

import com.bit.final_project.models.Inventory;
import com.bit.final_project.models.Purchase;
import com.bit.final_project.models.Supplier;
import com.bit.final_project.services.InventoryService;
import com.bit.final_project.services.PurchaseService;
import com.bit.final_project.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    SupplierService supplierService;
    @Override
    public Purchase createPurchase(int qty, String supplierId, String inventoryId) {
        Supplier supplier = supplierService.getSupplierbyId(supplierId);
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        return null;
    }
}
