package com.bit.final_project.models;
import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.enums.DoorFillingType;
import com.bit.final_project.enums.DoorType;
import com.bit.final_project.enums.Status;
import com.bit.final_project.enums.TypeOfBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "door")
@Slf4j
public class Door {
    @Id
    private String id;
    private String name;
    private String code;
    private String height;
    private String width;
    private String image;
    @Enumerated(EnumType.STRING)
    private DoorType type;
    @Column(name = "door_color")
    private String doorColor;
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

    public static Door init(DoorDto doorDto){
        log.info("door ={}",doorDto.getCode());
        Door door = new Door();
        door.setId(Generator.getUUID());
        door.setCode(doorDto.getCode());
        door.setName(doorDto.getName());
        door.setHeight(doorDto.getHeight());
        door.setWidth(doorDto.getWidth());
        log.info("befor if door type={}",doorDto.getType());
        if (doorDto.getType() != null && !doorDto.getType().isEmpty()) {
            log.info("door type={}",doorDto.getType());
            door.setType(DoorType.valueOf(doorDto.getType()));
        }

        door.setDoorColor(doorDto.getDoorColor());
        if (doorDto.getFillingType() != null && !doorDto.getFillingType().isEmpty()) {
            door.setFillingType(DoorFillingType.valueOf(doorDto.getFillingType()));
        }

        door.setBoardColor(doorDto.getBoardColor());
        door.setGlassColor(doorDto.getGlassColor());
        if (doorDto.getTypeOfBoard() != null && !doorDto.getTypeOfBoard().isEmpty()) {
            door.setTypeOfBoard(TypeOfBoard.valueOf(doorDto.getTypeOfBoard()));
        }

        door.setBoardThickness(doorDto.getBoardThickness());
        door.setGlassThickness(doorDto.getGlassThickness());

        door.setStatus(Status.INACTIVE);
        return door;
    }
}
