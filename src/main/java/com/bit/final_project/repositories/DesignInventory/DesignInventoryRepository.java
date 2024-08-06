package com.bit.final_project.repositories.DesignInventory;

import com.bit.final_project.models.Design;
import com.bit.final_project.models.DesignInventory;
import com.bit.final_project.models.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignInventoryRepository extends JpaRepository<DesignInventory,String> {

    List<DesignInventory> findAllByDesign(Design design);
}
