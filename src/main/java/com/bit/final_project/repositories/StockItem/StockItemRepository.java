package com.bit.final_project.repositories.StockItem;

import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.StockItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem,String> {
    Page<StockItem> findAllByStatusAndType(Pageable pageable, Status status, PRODUCT_TYPE type);
}
