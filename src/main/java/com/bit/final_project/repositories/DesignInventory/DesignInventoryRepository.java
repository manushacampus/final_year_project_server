package com.bit.final_project.repositories.DesignInventory;

import com.bit.final_project.models.DesignInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignInventoryRepository extends JpaRepository<DesignInventory,String> {
}
