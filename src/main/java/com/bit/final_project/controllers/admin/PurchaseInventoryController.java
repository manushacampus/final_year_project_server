package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.PurchaseDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee/purchase")
@Slf4j
public class PurchaseInventoryController {
    @Autowired
    PurchaseService purchaseService;
    @PostMapping()
    @Transactional
    public ResponseEntity<StandardResponse> purchaseInventory(@RequestParam("supplier") String supplier,
                                                              @RequestParam("inventory") String inventory,
                                                              @RequestParam("qty") int qty) throws MessagingException {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),
                        "success", PurchaseDto.init(purchaseService.createPurchase(qty,supplier,inventory) )),
                HttpStatus.OK
        );
    }
    @GetMapping(params ={"status","page","size"})
    public ResponseEntity<StandardResponse> getPurchaseInventoryByStatus( @Param("@status") String status,
                                                                           @Param("@page") int page,
                                                                           @Param("@size") int size){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",purchaseService.getJPurchaseByStatus(Status.valueOf(status),page,size)),HttpStatus.OK
        );
    }
}
