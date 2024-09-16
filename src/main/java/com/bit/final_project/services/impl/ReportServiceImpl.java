package com.bit.final_project.services.impl;

import com.bit.final_project.dto.ReportDto;
import com.bit.final_project.enums.PaymentType;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Grn;
import com.bit.final_project.models.Payment;
import com.bit.final_project.models.Salary;
import com.bit.final_project.models.Utility;
import com.bit.final_project.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Autowired
    PaymentService paymentService;
    @Autowired
    SalaryService salaryService;
    @Autowired
    GrnService grnService;

    @Autowired
    UtilityService utilityService;

    @Override
    public ReportDto getReport(String startDate, String endDate) {
        LocalDate lDate = LocalDate.parse(startDate);
        int year = lDate.getYear();
        int month = lDate.getMonthValue();
        // ORDER,QUOTATION PAyment Type
        ReportDto reportDto = new ReportDto();
        Double ProductOrder=0.0;
        Double ProductQuot=0.0;
        List<Payment> paymentList =paymentService.getAllPaymentByStatusAndTypeAndDate(Status.ACTIVE, PaymentType.ORDER,startDate);
        List<Payment> paymentListQuot =paymentService.getAllPaymentByStatusAndTypeAndDate(Status.ACTIVE, PaymentType.QUOTATION,startDate);

        for (Payment p:paymentList){
            log.info("=-=-=-=-=-=-={}",p.getCreated_at());
            ProductOrder=ProductOrder+p.getPrice();
        }
        for (Payment p:paymentListQuot){
            log.info("=-=-=-=-=-=-={}",p.getCreated_at());
            ProductQuot=ProductQuot+p.getPrice();
        }

        Double salary = 0.0;
        Double grn = 0.0;
        Double utility = 0.0;
        List<Salary> salaryList = salaryService.getSalaryByDate(year,month);
        List<Grn> grnList = grnService.getGrnByDate(year,month);
        List<Utility> utilityList = utilityService.getUtilityByDate(year,month);
        for (Salary p:salaryList){
            log.info("=-=-=-=-=-=-={}",p.getDate());
            salary=salary+p.getSalary();
        }
        for (Grn p:grnList){
            log.info("=-=-=-=-=-=-={}",p.getDate());
            grn=grn+p.getPrice();
        }
        for (Utility p:utilityList){
            log.info("=-=-=-=-=-=-={}",p.getDate());
            utility=utility+p.getPayment();
        }
        reportDto.setProductOrder(ProductOrder);
        reportDto.setQuotationOrder(ProductQuot);

        reportDto.setEmployeeSalary(salary);
        reportDto.setInventoryPurchase(grn);
        reportDto.setUtility(utility);

        reportDto.setIncomeTotal(ProductOrder+ProductQuot);
        reportDto.setOutcomeTotal(salary+grn+utility);

        reportDto.setProfit(reportDto.getIncomeTotal()-reportDto.getOutcomeTotal());
        return reportDto;

    }
}
