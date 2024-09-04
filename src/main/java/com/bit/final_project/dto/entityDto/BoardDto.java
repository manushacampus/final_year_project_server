package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.Bar;
import com.bit.final_project.models.Board;
import lombok.Data;

@Data
public class BoardDto {
    private String id;
    private String name;
    private Double weight;
    private Double height;
    private Double width;
    private String color;
    private String type;
    private String dec;

    public static BoardDto init(Board board){
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setName(board.getName());
        boardDto.setColor(board.getColor());
        boardDto.setWeight(board.getWeight());
        boardDto.setHeight(board.getHeight());
        boardDto.setType(board.getType());

        return boardDto;
    }
}
