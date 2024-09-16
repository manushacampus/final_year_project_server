package com.bit.final_project.dto;

import com.bit.final_project.dto.entityDto.OrderDto;
import lombok.Data;

import java.util.List;

@Data
public class DashboardDto {
    private int orders;
    private int customer;
    private int activeCustomer;
    private int employee;
    private int activeEmployee;
    private double profit;
    private int product;
    private int quotation;
    private int jobs;
    private int suppliers;
    private int inventory;
    private int delivers;
    private int design;
    private int payments;
    private int utility;

}
