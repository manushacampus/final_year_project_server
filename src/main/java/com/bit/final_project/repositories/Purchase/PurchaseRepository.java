package com.bit.final_project.repositories.Purchase;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Other;
import com.bit.final_project.models.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,String> {
    Page<Purchase> findAllByStatus(Pageable pageable, Status status);
}
