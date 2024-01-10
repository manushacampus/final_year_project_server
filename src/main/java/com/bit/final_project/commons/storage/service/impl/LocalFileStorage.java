package com.bit.final_project.commons.storage.service.impl;

import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.configs.GlobalConfigs;
import com.bit.final_project.exceptions.http.BadRequestException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import com.amazonaws.util.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@Getter
public class LocalFileStorage implements FilesStorageService{
    GlobalConfigs globalConfigs;
    public LocalFileStorage(GlobalConfigs globalConfigs){
        this.globalConfigs=globalConfigs;
    }

    @Override
    public void setup(){
        File tmpDir = new File(globalConfigs.getHomeDir());
        if (!tmpDir.isDirectory()) {
            throw new BadRequestException("there is no directory");
        }
    }
    @Override
    public File createFolder(AppFile appFile){
        File tmpDir = new File(globalConfigs.getHomeDir()+"\\"+appFile.getFolderName());
        if (!tmpDir.isDirectory()) {
            try {
                return Files.createDirectory(tmpDir.toPath()).toFile();
            } catch (IOException e) {
//                e.printStackTrace();
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
        return tmpDir;
    }
    @Override
    public AppFile save(AppFile appFile) {
          log.info("FileStorageImpl AppFile request InputStream={},imageName={}",appFile.getInputStream(),appFile.getImageName());
        try {
            Files.copy(appFile.getInputStream(), createFolder(appFile).toPath().resolve(appFile.getImageName()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return appFile;
    }
    @Override
    public ResponseEntity<byte[]> get(AppFile appFile) throws IOException {
        String file=globalConfigs.getHomeDir()+"/"+appFile.getFolderName()+"/"+appFile.getFileName();
        Path path = Paths.get(file);
        try (InputStream inputStream = Files.newInputStream(path, StandardOpenOption.READ)) {
            MimeType mimeType = MimeType.valueOf(Files.probeContentType(path));
            byte[] fileContent = IOUtils.toByteArray(inputStream); // Assuming you have Apache Commons IO

            return ResponseEntity.ok()
                    .contentType(MediaType.asMediaType(mimeType))
                    .body(fileContent);
        } catch (IOException e) {
            // Handle or log the exception as needed
            throw e;
        }
//        InputStream inputStream = Files.newInputStream(path, StandardOpenOption.READ);
//        MimeType mimeType = MimeType.valueOf(Files.probeContentType(path));
//        return ResponseEntity.ok().contentType(MediaType.asMediaType(mimeType)).body(IOUtils.toByteArray(inputStream));
    }
    @Override
    public AppFile delete(AppFile appFile) throws IOException{
        String file=globalConfigs.getHomeDir()+"/"+appFile.getFolderName()+"/"+appFile.getFileName();
        Path path = Paths.get(file);
            Files.delete(path);
            System.out.println("File or directory deleted successfully");
        return appFile;
    }

}
