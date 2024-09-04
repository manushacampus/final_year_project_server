package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.SupplierDto;
import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.StockItem;
import com.bit.final_project.models.Supplier;
import com.bit.final_project.repositories.Supplier.SupplierRepository;
import com.bit.final_project.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        supplier.setContact(request.getContact());
        supplier.setNic(request.getNic());
        supplier.setSupplier_type(request.getSupplierType());
        return supplierRepository.save(supplier);
    }

    @Override
    public Page<Supplier> getAllSupplierByStatus(int page, int size, String status) {
        Pageable pageableRequest = PageRequest.of(page,size);
        Page<Supplier> suppliers = supplierRepository.findAllByStatus(pageableRequest, Status.valueOf(status));
        return suppliers;
    }
}
