package com.bit.final_project.dto.entityDto;

import com.bit.final_project.commons.URL;
import com.bit.final_project.models.Windows;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowDto {
    private String id;
    private String name;
    private String code;
    private String height;
    private String width;
    private String colorType;
    private String type;
    private String windowColor;
    private String fillingType;
    private String glassColor;
    private String boardColor;
    private String typeOfBoard;
    private String boardThickness;
    private String glassThickness;
    private String status;
    private String image;
    private Double price;

    public static WindowDto init(Windows windows){
        WindowDto windowDto = new WindowDto();
        windowDto.setId(windows.getId());
        windowDto.setName(windows.getName());
        windowDto.setCode(windows.getCode());
        windowDto.setHeight(windows.getHeight());
        windowDto.setWidth(windows.getWidth());
        windowDto.setStatus(String.valueOf(windows.getStatus()));
        if (windows != null && windows.getImage() != null && !windows.getImage().isEmpty()) {
            windowDto.setImage(URL.fileStorageUrl.replace("{type}", "product").replace("{fileName}", windows.getImage()));
        }
        else {
            windowDto.setImage(URL.fileStorageUrl.replace("{type}", "default").replace("{fileName}", "No_Image_Available.jpg"));
        }
        return windowDto;
    }
}
