package com.bit.final_project.repositories.StockItem;

import com.bit.final_project.models.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem,String> {
}
