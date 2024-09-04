package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.DesignInventoryDto;
import com.bit.final_project.dto.entityDto.QuotationDto;
import com.bit.final_project.models.DesignInventory;
import com.bit.final_project.models.Quotation;

public class QuotationMapper {
    public static QuotationDto convertToDto(Quotation quotation){
        QuotationDto quotationDto = new QuotationDto();
        quotationDto.setId(quotation.getId());
        quotationDto.setStatus(String.valueOf(quotation.getStatus()));
        quotationDto.setType(String.valueOf(quotation.getType()));
        if (quotation.getDoorQuotation() != null) {
            quotationDto.setDoorQuotation(DoorQuotationMapper.convertToDTO(quotation.getDoorQuotation()));
        }
        if (quotation.getWindowQuotation() != null) {
//            quotationDto.setWindowQuotation(quotation.getDoorQuotation());
        }
        if (quotation.getCustomer() != null) {
            quotationDto.setCustomer(CustomerMapper.convertToDTO(quotation.getCustomer()));
        }

        return quotationDto;
    }
}
