package com.bit.final_project.services;

import com.bit.final_project.dto.ReportDto;

public interface ReportService {

    ReportDto getReport(String startDate,String endDate);
}
