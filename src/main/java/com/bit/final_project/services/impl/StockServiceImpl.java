package com.bit.final_project.services.impl;

import com.bit.final_project.commons.URL;
import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Job;
import com.bit.final_project.models.StockItem;
import com.bit.final_project.repositories.StockItem.StockItemRepository;
import com.bit.final_project.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockItemRepository stockItemRepository;
    @Override
    public StockItem getStockItemById(String id) {
        return stockItemRepository.findById(id).orElseThrow(()-> new EntityExistsException("StockItem not found with id: " + id));
    }

    @Override
    public Page<StockItem> getAllStockItemByStatusAndType(int page, int size, String status, String productType) {
        Pageable pageableRequest = PageRequest.of(page,size);
        Page<StockItem> stockItems = stockItemRepository.findAllByStatusAndType(pageableRequest,Status.valueOf(status),PRODUCT_TYPE.valueOf(productType));
        stockItems.getContent().forEach(stockItem ->{
            if (stockItem.getDoor() != null && stockItem.getDoor().getImage() != null) {
                stockItem.getDoor().setImage(URL.fileStorageUrl.replace("{type}", "product").replace("{fileName}", stockItem.getDoor().getImage()));
            }
        });
        return stockItems;
    }
}
