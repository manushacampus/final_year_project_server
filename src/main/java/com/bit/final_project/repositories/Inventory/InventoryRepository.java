package com.bit.final_project.repositories.Inventory;

import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.models.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {

    Page<Inventory> findByInventoryType(Pageable pageable, INVENTORY_TYPE type);
    List<Inventory> findByInventoryTypeAndCode(INVENTORY_TYPE type,String code);
}
