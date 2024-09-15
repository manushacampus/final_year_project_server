package com.bit.final_project.dto.entityDto;

import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.models.Job;
import com.bit.final_project.models.Quotation;
import lombok.Data;

@Data
public class JobDto {
    private String id;
    private String jobCode;
    private String type;
    private String start;
    private String end;
    private String creationType;
    private String progress;
    private String dueDate;
    private String description;
    private int qty;
    private Double unitPrice;
    private String status;
    private StockItemDto stockItem;
    private QuotationDto quotation;

    public static JobDto init(Job job){
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setJobCode(job.getJobCode());
        jobDto.setType(String.valueOf(job.getType()));
        jobDto.setProgress(String.valueOf(job.getProgress()));
        jobDto.setCreationType(String.valueOf(job.getCreation_type()));
        if (job.getStockItem()!=null){
            jobDto.setStockItem(StockItemDto.init(job.getStockItem()));
        }
        if (job.getQuotation()!=null){
            jobDto.setQuotation(QuotationMapper.convertToDto(job.getQuotation()));
        }
        jobDto.setDescription(job.getDescription());
        jobDto.setDueDate(String.valueOf(job.getDueDate()));
        jobDto.setQty(job.getQty());
        jobDto.setStatus(String.valueOf(job.getStatus()));
//        jobDto.setCreateBy(EmployeeDto.init(job.getCreateBy()));

        return jobDto;
    }
}
