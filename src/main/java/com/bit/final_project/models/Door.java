package com.bit.final_project.models;
import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "door")
public class Door {
    @Id
    private String id;
    private String name;
    private String code;
    private String height;
    private String width;
    @Enumerated(EnumType.STRING)
    private Status status;

    public static Door init(DoorDto doorDto){
        Door door = new Door();
        door.setId(Generator.getUUID());
        door.setName(doorDto.getName());
        door.setHeight(doorDto.getHeight());
        door.setWidth(doorDto.getWidth());
        door.setStatus(Status.INACTIVE);
        return door;
    }
}
