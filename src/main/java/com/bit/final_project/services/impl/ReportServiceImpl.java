package com.bit.final_project.services.impl;

import com.bit.final_project.dto.ReportDto;
import com.bit.final_project.services.PaymentService;
import com.bit.final_project.services.ReportService;
import com.bit.final_project.services.SalaryService;
import com.bit.final_project.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    PaymentService paymentService;
    @Autowired
    SalaryService salaryService;

    @Autowired
    UtilityService utilityService;

    @Override
    public ReportDto getReport(String startDate, String endDate) {
        return null;
    }
}
