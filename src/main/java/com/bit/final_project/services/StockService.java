package com.bit.final_project.services;

import com.bit.final_project.models.Job;
import com.bit.final_project.models.StockItem;

public interface StockService {
    StockItem getStockItemById(String id);
}
