package com.bit.final_project.controllers;

import com.amazonaws.util.IOUtils;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api")
public class FileController {
    @Autowired
    FilesStorageService storageService;
    @GetMapping("/file/{type}/{fileName}")
    public ResponseEntity<byte[]> file(@PathVariable String fileName, @PathVariable String type) throws IOException {
        log.info("get Request FileName={},FolderType={}",fileName,type);
        AppFile file=this.storageService.read(new AppFile(type,fileName));
        return ResponseEntity.ok().contentType(MediaType.asMediaType(file.getContentType())).body(IOUtils.toByteArray(file.getInputStream()));
    }
//    @DeleteMapping("/file/{type}/{fileName}")
//    public SFile fileDelete(@PathVariable String fileName, @PathVariable String type){
//        log.info("delete Request FileName={},FolderType={}",fileName,type);
//        SFile file=storageService.delete(new SFile(type,fileName));
//        return file;
//    }
}