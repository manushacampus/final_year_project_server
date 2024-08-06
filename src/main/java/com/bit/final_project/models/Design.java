package com.bit.final_project.models;

import com.bit.final_project.dto.entityDto.DesignDto;
import com.bit.final_project.enums.DESIGN_TYPE;
import com.bit.final_project.enums.DoorFillingType;
import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "design")
@Slf4j
public class Design {
    @Id
    private String id;
    private String name;
    private String code;
    private String image;
    @Enumerated(EnumType.STRING)
    private DESIGN_TYPE type;
    @Enumerated(EnumType.STRING)
    private Status status;

    public static Design init(DesignDto request){
        Design design = new Design();
        design.setId(request.getId());
        design.setCode(request.getCode());
        design.setName(request.getName());
        if (request.getStatus() != null && !request.getStatus().isEmpty()) {
            design.setStatus(Status.valueOf(request.getStatus()));
        }
        if (request.getType() != null && !request.getType().isEmpty()) {
            design.setType(DESIGN_TYPE.valueOf(request.getType()));
        }
        return design;
    }
}
