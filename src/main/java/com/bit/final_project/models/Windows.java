package com.bit.final_project.models;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.WindowDto;
import com.bit.final_project.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "windows")
public class Windows {
    @Id
    private String id;
    private String name;
    private String code;
    private String height;
    private String width;
    private String image;
    @Enumerated(EnumType.STRING)
    private DoorType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "color_type")
    private ColorType colorType;
    @Column(name = "window_color")
    private String windowColor;
    @Enumerated(EnumType.STRING)
    @Column(name = "filling_type")
    private DoorFillingType FillingType;
    @Column(name = "glass_color")
    private String glassColor;
    @Column(name = "board_color")
    private String boardColor;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_board")
    private TypeOfBoard typeOfBoard;
    @Column(name = "board_thickness")
    private String boardThickness;
    @Column(name = "glass_thickness")
    private String glassThickness;
    @Enumerated(EnumType.STRING)
    private Status status;
    public static Windows init(WindowDto windowDto){
        Windows windows = new Windows();
        windows.setId(Generator.getUUID());
        windows.setCode(windowDto.getCode());
        windows.setName(windowDto.getName());
        windows.setHeight(windowDto.getHeight());
        windows.setWidth(windowDto.getWidth());
        if (windowDto.getType() != null && !windowDto.getType().isEmpty()) {
            windows.setType(DoorType.valueOf(windowDto.getType()));
        }

        windows.setWindowColor(windowDto.getWindowColor());
        if (windowDto.getFillingType() != null && !windowDto.getFillingType().isEmpty()) {
            windows.setFillingType(DoorFillingType.valueOf(windowDto.getFillingType()));
        }

        windows.setBoardColor(windowDto.getBoardColor());
        windows.setGlassColor(windowDto.getGlassColor());
        if (windowDto.getTypeOfBoard() != null && !windowDto.getTypeOfBoard().isEmpty()) {
            windows.setTypeOfBoard(TypeOfBoard.valueOf(windowDto.getTypeOfBoard()));
        }

        windows.setBoardThickness(windowDto.getBoardThickness());
        windows.setGlassThickness(windowDto.getGlassThickness());

        windows.setStatus(Status.INACTIVE);
        return windows;
    }
}
