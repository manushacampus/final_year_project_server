package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.Supplier;
import lombok.Data;

@Data
public class SupplierDto {
    private String id;
    private String firstName;
    private String lastName;
    private String nic;
    private String supplierType;
    private String email;
    private String contact;

    public static SupplierDto init(Supplier supplier){
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setId(supplier.getId());
        supplierDto.setEmail(supplier.getEmail());
        supplierDto.setFirstName(supplier.getFirst_name());
        supplierDto.setLastName(supplier.getLast_name());
        supplierDto.setNic(supplier.getNic());
        supplierDto.setContact(supplier.getContact());
        supplierDto.setSupplierType(supplier.getSupplier_type());

        return supplierDto;
    }
}
