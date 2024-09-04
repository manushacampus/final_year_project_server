package com.bit.final_project.repositories.PurchaseRequest;

import com.bit.final_project.models.PurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest,String> {
}
