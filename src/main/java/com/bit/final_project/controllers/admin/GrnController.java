package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.GrnImageDto;
import com.bit.final_project.dto.RequestGrnDto;
import com.bit.final_project.dto.UtilityImageDto;
import com.bit.final_project.dto.entityDto.GrnDto;
import com.bit.final_project.dto.entityDto.UtilityDto;
import com.bit.final_project.services.GrnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee/grn")
@Slf4j
public class GrnController {
    @Autowired
    GrnService grnService;
    @PostMapping("/{id}")
    public @ResponseBody
    ResponseEntity<StandardResponse> save(@PathVariable("id") String id,@ModelAttribute GrnImageDto request) throws IOException {
        RequestGrnDto requestGrnDto = JSON.stringToObject(request.getGrnDto(), RequestGrnDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",
                        GrnDto.init(grnService.createGrn(requestGrnDto,id, request.getInvoice()))),HttpStatus.OK
        );
    }
    @GetMapping(value = "/all/{id}")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getGrnAll(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", grnService.getGrnByPurchase(id).stream().map(GrnDto::init).collect(Collectors.toList())),HttpStatus.OK
        );
    }
}
