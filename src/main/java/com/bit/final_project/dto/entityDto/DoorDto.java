package com.bit.final_project.dto.entityDto;

import com.bit.final_project.commons.URL;
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
        if (door != null && door.getImage() != null && !door.getImage().isEmpty()) {
            doorDto.setImage(URL.fileStorageUrl.replace("{type}", "product").replace("{fileName}", door.getImage()));
        }
        else {
            doorDto.setImage(URL.fileStorageUrl.replace("{type}", "default").replace("{fileName}", "No_Image_Available.jpg"));
        }
        return doorDto;
    }
}
