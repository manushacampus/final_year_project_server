package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.Customer;
import com.bit.final_project.models.Feedback;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class OrderDto {
    private String id;
    private Double total;
    private String createdAt;
    private String updatedAt;
    private String status;
    private String OrderType;
    private CustomerDto customer;
    private FeedbackDto feedback;
}
