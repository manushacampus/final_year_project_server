package com.bit.final_project.controllers.customer;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.services.DoorService;
import com.bit.final_project.services.QuotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/customer/quotation")
@Slf4j
public class CQuotationController {
    @Autowired
    QuotationService quotationService;

    @PostMapping("/door")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> createDoorQuotation(@RequestBody DoorQuotationDto doorDto){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(
                        quotationService.createDoorQuotation(doorDto))),HttpStatus.OK
        );
    }
}
