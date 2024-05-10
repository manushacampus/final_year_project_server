package com.bit.final_project.dto.entityDto;

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
    private String category;

    public DoorDto init(Door door){
        DoorDto doorDto = new DoorDto();
        doorDto.setId(door.getId());
        doorDto.setName(door.getName());
        return doorDto;
    }
}
