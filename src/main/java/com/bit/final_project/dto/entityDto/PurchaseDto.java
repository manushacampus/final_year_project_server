package com.bit.final_project.dto.entityDto;

import com.bit.final_project.enums.Status;
import com.bit.final_project.mapper.InventoryMapper;
import com.bit.final_project.models.Inventory;
import com.bit.final_project.models.Purchase;
import com.bit.final_project.models.Supplier;
import lombok.Data;

import javax.persistence.*;
@Data
public class PurchaseDto {
    private String id;
    private int qty;

    private String status;
    private SupplierDto supplier;
    private InventoryDto inventory;

    public static PurchaseDto init(Purchase purchase){
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setQty(purchase.getQty());
        purchaseDto.setSupplier(SupplierDto.init(purchase.getSupplier()));
        purchaseDto.setInventory(InventoryMapper.convertToDto(purchase.getInventory()));
        return purchaseDto;
    }
}
