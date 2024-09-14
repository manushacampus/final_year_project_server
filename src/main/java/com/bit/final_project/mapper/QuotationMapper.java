package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.DesignInventoryDto;
import com.bit.final_project.dto.entityDto.QuotationDto;
import com.bit.final_project.models.DesignInventory;
import com.bit.final_project.models.Quotation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class QuotationMapper {
    public static QuotationDto convertToDto(Quotation quotation){
        QuotationDto quotationDto = new QuotationDto();
        quotationDto.setId(quotation.getId());
        quotationDto.setQty(quotation.getQty());
        quotationDto.setStatus(String.valueOf(quotation.getStatus()));
        quotationDto.setType(String.valueOf(quotation.getType()));

        if (quotation.getCreated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(quotation.getCreated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            quotationDto.setCreatedAt(localDateTime.format(formatter));
        }
        if (quotation.getUpdated_at() != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(quotation.getUpdated_at(), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            quotationDto.setUpdatedAt(localDateTime.format(formatter));
        }

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
