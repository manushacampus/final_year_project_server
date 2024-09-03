package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.SupplierDto;
import com.bit.final_project.models.Supplier;
import com.bit.final_project.repositories.Supplier.SupplierRepository;
import com.bit.final_project.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Supplier saveSupplier(SupplierDto request) {
        Supplier supplier = new Supplier();
        supplier.setId(Generator.getUUID());
        supplier.setFirst_name(request.getFirstName());
        supplier.setLast_name(request.getLastName());
        supplier.setEmail(request.getEmail());
        supplier.setSupplier_type(request.getSupplierType());
        return supplierRepository.save(supplier);
    }

    @Override
    public Page<Supplier> getAllSupplierByStatus(int page, int size, String status) {
        return null;
    }
}
