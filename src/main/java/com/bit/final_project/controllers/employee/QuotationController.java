package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.services.QuotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/employee/quotation")
@Slf4j
public class QuotationController {
    @Autowired
    QuotationService quotationService;

    @GetMapping(value = "/all", params = {"status"})
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> createDoorQuotation(@Param("@status") String status){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", quotationService.getAllQuotationByStatus(status).stream().map(QuotationMapper::convertToDto)),HttpStatus.OK
        );
    }
}
