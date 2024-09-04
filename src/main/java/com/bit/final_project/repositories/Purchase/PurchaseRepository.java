package com.bit.final_project.repositories.Purchase;

import com.bit.final_project.models.Other;
import com.bit.final_project.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,String> {
}
