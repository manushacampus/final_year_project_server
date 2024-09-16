package com.bit.final_project.dto.entityDto;

import com.bit.final_project.commons.URL;
import com.bit.final_project.models.Grn;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class GrnDto {
    private String id;
    private int qty;
    private Double price;
    private String date;

    private String invoiceNo;
    private String invoice;

    private PurchaseDto purchase;

    public static GrnDto init(Grn grn){
        GrnDto grnDto = new GrnDto();
        grnDto.setId(grn.getId());
        grnDto.setPrice(grn.getPrice());
        grnDto.setQty(grn.getQty());
        grnDto.setInvoiceNo(grn.getInvoiceNo());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        grnDto.setDate(grn.getDate().format(formatter));
        grnDto.setInvoice(URL.fileStorageUrl.replace("{type}","grn").replace("{fileName}",grn.getInvoice()));

        return grnDto;
    }
}
