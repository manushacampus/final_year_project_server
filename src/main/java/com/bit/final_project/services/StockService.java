package com.bit.final_project.services;

import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Job;
import com.bit.final_project.models.StockItem;
import org.springframework.data.domain.Page;

public interface StockService {
    StockItem getStockItemById(String id);
    Page<StockItem> getAllStockItemByStatusAndType(int page, int size, String status, String productType);
}
