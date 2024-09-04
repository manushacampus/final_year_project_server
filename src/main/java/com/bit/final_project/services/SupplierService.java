package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.SupplierDto;
import com.bit.final_project.models.Supplier;
import org.springframework.data.domain.Page;

public interface SupplierService {
    Supplier getSupplierById(String id);
    Supplier saveSupplier(SupplierDto request);
    Page<Supplier> getAllSupplierByStatus(int page, int size,String status);
}
