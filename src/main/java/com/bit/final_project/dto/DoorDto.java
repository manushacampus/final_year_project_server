package com.bit.final_project.dto;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.models.Door;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoorDto {
    private String id;
    private String name;
    private String height;
    private String width;

    public DoorDto init(Door door){
        DoorDto doorDto = new DoorDto();
        doorDto.setId(door.getId());
        doorDto.setName(door.getName());
        return doorDto;
    }
}
