package com.bit.final_project.dto.entityDto;

import com.bit.final_project.commons.URL;
import com.bit.final_project.enums.ColorType;
import com.bit.final_project.models.Door;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DoorDto {
    private String id;
    private String name;
    private String code;
    private String height;
    private String width;
    private String colorType;
    private String type;
    private String doorColor;
    private String fillingType;
    private String glassColor;
    private String boardColor;
    private String typeOfBoard;
    private String boardThickness;
    private String glassThickness;
    private String status;
    private String image;
    private Double price;

    public static DoorDto init(Door door){
        DoorDto doorDto = new DoorDto();
        doorDto.setId(door.getId());
        doorDto.setName(door.getName());
        doorDto.setCode(door.getCode());
        doorDto.setHeight(door.getHeight());
        doorDto.setWidth(door.getWidth());
        doorDto.setDoorColor(doorDto.getDoorColor());

        if (door.getColorType()!=null){
            doorDto.setColorType(String.valueOf(door.getColorType()));
        }
        if (door.getFillingType()!=null){
            doorDto.setFillingType(String.valueOf(door.getFillingType()));
        }
        if (door.getTypeOfBoard()!=null){
            doorDto.setTypeOfBoard(String.valueOf(door.getTypeOfBoard()));
        }
        if (door.getType()!=null){
            doorDto.setType(String.valueOf(door.getType()));
        }
        if (door.getBoardColor()!=null){
            doorDto.setBoardColor(door.getBoardColor());
        }
        if (door.getBoardThickness()!=null){
            doorDto.setBoardThickness(door.getBoardThickness());
        }
        if (door.getGlassColor()!=null){
            doorDto.setGlassColor(door.getGlassColor());
        }
        if (door.getGlassThickness()!=null){
            doorDto.setGlassThickness(door.getGlassThickness());
        }


        doorDto.setStatus(String.valueOf(door.getStatus()));
        if (door != null && door.getImage() != null && !door.getImage().isEmpty()) {
            doorDto.setImage(URL.fileStorageUrl.replace("{type}", "product").replace("{fileName}", door.getImage()));
        }
        else {
            doorDto.setImage(URL.fileStorageUrl.replace("{type}", "default").replace("{fileName}", "No_Image_Available.jpg"));
        }
        return doorDto;
    }
}
