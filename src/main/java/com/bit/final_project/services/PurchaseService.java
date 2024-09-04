package com.bit.final_project.services;

import com.bit.final_project.models.Purchase;

public interface PurchaseService {
    Purchase createPurchase(int qty,String supplier,String inventory);
}
