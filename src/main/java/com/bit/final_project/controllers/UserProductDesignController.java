package com.bit.final_project.controllers;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.services.DesignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product-design")
@Slf4j
public class UserProductDesignController {
    @Autowired
    DesignService designService;
    @GetMapping(params = {"status","type","page", "size"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getAllPage(@Param("@status") String status,
                                                @Param("@type") String type,
                                                @Param("@page") int page,
                                                @Param("@size") int size) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", designService.getALlDesignByStatusPage(status,page,size)),HttpStatus.OK
        );
    }
}
