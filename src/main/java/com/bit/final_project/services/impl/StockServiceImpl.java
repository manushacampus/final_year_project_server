package com.bit.final_project.services.impl;

import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Job;
import com.bit.final_project.models.StockItem;
import com.bit.final_project.repositories.StockItem.StockItemRepository;
import com.bit.final_project.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockItemRepository stockItemRepository;
    @Override
    public StockItem getStockItemById(String id) {
        return stockItemRepository.findById(id).orElseThrow(()-> new EntityExistsException("StockItem not found with id: " + id));
    }
}
