package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.security.filters.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee/purchase")
@Slf4j
public class PurchaseInventoryController {
    @PostMapping()
    public ResponseEntity<StandardResponse> purchaseInventory(@RequestParam("supplier") String status,
                                                              @RequestParam("inventory") String inventory,
                                                              @RequestParam("qty") int qty){
        log.info("status={}", Status.valueOf(status));
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),
                        "success", "s"),
                HttpStatus.OK
        );
    }
}
