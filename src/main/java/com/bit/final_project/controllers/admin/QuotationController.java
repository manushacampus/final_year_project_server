package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.OrderCompleteDto;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.mapper.OrderMapper;
import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.services.QuotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;

@RestController
@RequestMapping("api/employee/quotation")
@Slf4j
public class QuotationController {
    @Autowired
    QuotationService quotationService;

    @GetMapping(value = "/all", params = {"page","size","status","type"})
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getQuotationAll(@Param("@page") int page,
                                                     @Param("@size") int size,
                                                     @Param("@status") String status,
                                                     @Param("@type") String type){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", quotationService.getAllQuotationByStatus(page,size,status,type)),HttpStatus.OK
        );
    }
    @GetMapping(value = "/{id}")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getQuotationByID(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.getQuotationById(id))),HttpStatus.OK
        );
    }
    @PutMapping(value = "/change")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> changeQuotationType( @RequestParam("id") String id,
                                                          @RequestParam("type") String type
                                                          ){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.changeQuotationType(id,type)) ),HttpStatus.OK
        );
    }

    @PostMapping(value = "/approve/{id}")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> approvedQuotation(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.approvedQuotation(id))),HttpStatus.OK
        );
    }
    @PutMapping("/deliver/{id}")
    public ResponseEntity<StandardResponse> deliverOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.deliverOrder(id))),HttpStatus.OK
        );
    }
    @PutMapping("/delivered/{id}")
    public ResponseEntity<StandardResponse> deliveredOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.deliveredOrder(id))),HttpStatus.OK
        );
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<StandardResponse> cancelOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.cancelOrder(id))),HttpStatus.OK
        );
    }
    @PutMapping("/complete/{id}")
    @Transactional
    public ResponseEntity<StandardResponse> completeOrder(@PathVariable("id") String id,@ModelAttribute OrderCompleteDto request) throws IOException {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", QuotationMapper.convertToDto(quotationService.completeOrder(id,request))),HttpStatus.OK
        );
    }
}
