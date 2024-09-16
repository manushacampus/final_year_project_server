package com.bit.final_project.controllers.customer;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.dto.entityDto.QuotationDto;
import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.services.DoorService;
import com.bit.final_project.services.QuotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

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
    @PostMapping("/window")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> createWindowQuotation(@RequestBody WindowQuotationDto windowQuotationDto){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(
                        quotationService.createWindowQuotation(windowQuotationDto))),HttpStatus.OK
        );
    }
    @PostMapping(value = "/cal")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> calculateQuotation(@RequestBody DoorQuotationDto doorDto){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", quotationService.calQuotation(doorDto) ),HttpStatus.OK
        );
    }
    @GetMapping(value = "")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getQuotationByUser(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", quotationService.getAllByUser().stream().map(QuotationMapper::convertToDto).collect(Collectors.toList()) ),HttpStatus.OK
        );
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<StandardResponse> cancelOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.cancelOrder(id))),HttpStatus.OK
        );
    }
}
