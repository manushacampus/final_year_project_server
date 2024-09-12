package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.CartDto;
import com.bit.final_project.dto.entityDto.StockItemDto;
import com.bit.final_project.models.Cart;

public class CartMapper {
    public static CartDto convertToDTO(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setQty(cart.getQty());
        dto.setCustomer(CustomerMapper.convertToDTO(cart.getCustomer()));
        dto.setStockItem(StockItemDto.init(cart.getStockItem()));
        return dto;
    }
}
