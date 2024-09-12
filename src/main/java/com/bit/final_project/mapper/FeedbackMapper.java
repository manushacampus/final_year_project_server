package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.FeedbackDto;
import com.bit.final_project.models.Feedback;
import com.bit.final_project.models.Order;

public class FeedbackMapper {
    public static FeedbackDto convertToDTO(Feedback feedback) {
        FeedbackDto dto = new FeedbackDto();
        if (feedback!=null){
            dto.setId(feedback.getId());
        }

        return dto;
    }
}
