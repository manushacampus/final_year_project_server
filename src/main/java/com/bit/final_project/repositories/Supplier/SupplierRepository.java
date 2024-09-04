package com.bit.final_project.repositories.Supplier;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,String> {
    Page<Supplier> findAllByStatus(Pageable pageable, Status status);
}
