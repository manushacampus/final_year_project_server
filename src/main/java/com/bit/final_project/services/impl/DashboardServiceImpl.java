package com.bit.final_project.services.impl;

import com.bit.final_project.dto.DashboardDto;
import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ReportService reportService;
    @Autowired
    StockService stockService;
    @Autowired
    QuotationService quotationService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    InventoryService inventoryService;
    @Override
    public DashboardDto getDashboard() {
        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setOrders(orderService.getTotalOrders("ACTIVE"));
        dashboardDto.setCustomer(customerService.getCustomerByStatus(0,100,"ACTIVE").getContent().size());
        dashboardDto.setActiveCustomer(customerService.getCustomerByStatus(0,100,"ACTIVE").getContent().size());
        dashboardDto.setEmployee(employeeService.getAll(0,100).getContent().size());
        dashboardDto.setActiveEmployee(employeeService.getAllActiveEmployeeCount());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDate.now().format(formatter);
        dashboardDto.setProfit(reportService.getReport(date,"").getProfit());

//        dashboardDto.setProduct(stockService.getAllStockItemByStatusAndType(0,100,"ACTIVE","DOOR").getContent().size());
//        dashboardDto.setProduct(dashboardDto.getProduct()+stockService.getAllStockItemByStatusAndType(0,100,"ACTIVE","WINDOWS").getContent().size());

        dashboardDto.setQuotation(quotationService.getAllQuotationByStatus(0,100,"ACTIVE","PENDING").getContent().size());

        dashboardDto.setSuppliers(supplierService.getAllSupplierByStatus(0,100,"ACTIVE").getContent().size());
        dashboardDto.setInventory(inventoryService.getInventoryByType(INVENTORY_TYPE.ALL,0,100).getContent().size());
        return dashboardDto;
    }
}
