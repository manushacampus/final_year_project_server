package com.bit.final_project.commons.storage.model;

import com.bit.final_project.commons.Generator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@Getter
@Setter
@Slf4j
@Builder
@AllArgsConstructor
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
    public AppFile(String type,String file){
        this.fileName=file;
        this.folderName=type;
    }
    public String getImageName(){
        return this.imageName+"."+this.extension;
    }
    public String getRelativePathAsString() {
        return this.folderName + "/" + this.fileName;
    }
    public MediaType getContentType() {
        String mimeTypeStr = URLConnection.guessContentTypeFromName(this.fileName);
        return MediaType.valueOf(mimeTypeStr);
    }
}
