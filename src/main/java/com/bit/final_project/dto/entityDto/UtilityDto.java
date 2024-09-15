package com.bit.final_project.dto.entityDto;

import com.bit.final_project.commons.URL;
import com.bit.final_project.models.Utility;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class UtilityDto {
    private String id;
    private String name;
    private String type;
    private String billNo;
    private String date;
    private Double payment;
    private String bill;
    private String status;

    public  static UtilityDto init(Utility utility){
        UtilityDto utilityDto = new UtilityDto();
        utilityDto.setId(utility.getId());
        utilityDto.setName(utility.getName());
        utilityDto.setBillNo(utility.getBillNo());
        utilityDto.setPayment(utility.getPayment());
        utilityDto.setStatus(String.valueOf(utility.getStatus()));
        utilityDto.setType(utility.getType());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        utilityDto.setDate(utility.getDate().format(formatter));
        utilityDto.setBill(URL.fileStorageUrl.replace("{type}","utility").replace("{fileName}",utility.getBill()));
        return utilityDto;
    }
}
