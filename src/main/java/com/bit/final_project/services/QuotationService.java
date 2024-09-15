package com.bit.final_project.services;

import com.bit.final_project.dto.OrderCompleteDto;
import com.bit.final_project.dto.QuotationPriceDto;
import com.bit.final_project.dto.entityDto.DoorQuotationDto;
import com.bit.final_project.dto.entityDto.QuotationDto;
import com.bit.final_project.dto.entityDto.WindowQuotationDto;
import com.bit.final_project.models.Order;
import com.bit.final_project.models.Quotation;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface QuotationService {
    Quotation getQuotationById(String id);
    Quotation createDoorQuotation(DoorQuotationDto dto);
    Quotation createWindowQuotation(WindowQuotationDto dto);
    Page<QuotationDto> getAllQuotationByStatus(int page, int size, String status, String orderStatus);

    Quotation changeQuotationType(String id,String type);

    QuotationPriceDto calQuotation(DoorQuotationDto dto);

    List<Quotation> getAllByUser();

    Quotation approvedQuotation(String id);

    Quotation deliverOrder(String orderId);
    Quotation deliveredOrder(String orderId);
    Quotation cancelOrder(String orderId);
    Quotation completeOrder(String orderId, OrderCompleteDto request) throws IOException;

}
