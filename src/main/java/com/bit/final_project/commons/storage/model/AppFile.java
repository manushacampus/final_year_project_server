package com.bit.final_project.commons.storage.model;

import com.bit.final_project.commons.Generator;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
@Getter
@Setter
public class AppFile {
    private String fileName;
    private InputStream inputStream;
    private String extension;
    private String folderName;
    private String imageName;

    public AppFile(String folder, String fileName, String extension, InputStream inputStreamFile) throws IOException {
        this.inputStream=inputStreamFile;
        this.fileName=fileName;
        this.folderName=folder;
        this.extension=extension;
        this.imageName= Generator.getUUID();
    }
    public AppFile(String file,String type){
        this.fileName=file;
        this.folderName=type;
    }
    public String getImageName(){
        return this.imageName+"."+this.extension;
    }
}
