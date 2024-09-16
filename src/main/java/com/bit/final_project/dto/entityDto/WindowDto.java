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
        windowDto.setWindowColor(windows.getWindowColor());
        windowDto.setStatus(String.valueOf(windows.getStatus()));


        if (windows.getColorType()!=null){
            windowDto.setColorType(String.valueOf(windows.getColorType()));
        }
        if (windows.getFillingType()!=null){
            windowDto.setFillingType(String.valueOf(windows.getFillingType()));
        }
        if (windows.getTypeOfBoard()!=null){
            windowDto.setTypeOfBoard(String.valueOf(windows.getTypeOfBoard()));
        }
        if (windows.getType()!=null){
            windowDto.setType(String.valueOf(windows.getType()));
        }
        if (windows.getBoardColor()!=null){
            windowDto.setBoardColor(windows.getBoardColor());
        }
        if (windows.getBoardThickness()!=null){
            windowDto.setBoardThickness(windows.getBoardThickness());
        }
        if (windows.getGlassColor()!=null){
            windowDto.setGlassColor(windows.getGlassColor());
        }
        if (windows.getGlassThickness()!=null){
            windowDto.setGlassThickness(windows.getGlassThickness());
        }


        if (windows != null && windows.getImage() != null && !windows.getImage().isEmpty()) {
            windowDto.setImage(URL.fileStorageUrl.replace("{type}", "product").replace("{fileName}", windows.getImage()));
        }
        else {
            windowDto.setImage(URL.fileStorageUrl.replace("{type}", "default").replace("{fileName}", "No_Image_Available.jpg"));
        }
        return windowDto;
    }
}
