package com.bit.final_project.models;
import com.bit.final_project.dto.entityDto.DoorDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "door")
public class Door {
    @Id
    private String id;
    private String name;
    private String height;
    private String width;

    public static Door init(DoorDto doorDto){
        Door door = new Door();
        door.setName(doorDto.getName());
        door.setHeight(doorDto.getHeight());
        door.setWidth(doorDto.getWidth());
        return door;
    }
}
