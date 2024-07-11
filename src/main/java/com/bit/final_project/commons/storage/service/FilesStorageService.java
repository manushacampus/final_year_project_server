package com.bit.final_project.commons.storage.service;

import com.bit.final_project.commons.storage.model.AppFile;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;


public interface FilesStorageService {
    public void setup();
    public File createFolder(AppFile file);
    public AppFile save(AppFile file);
    public ResponseEntity<byte[]> get(AppFile appFile) throws IOException;
    public AppFile read(AppFile sFile);

    AppFile delete(AppFile appFile) throws IOException;

}
