package com.example.demo.controller;

import java.util.List;

import com.example.demo.models.File;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.payload.response.UploadFileResponse;
import com.example.demo.security.services.upload.FilesStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(value = "File upload Rest Service")
@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    FilesStorageService storageService;

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @ApiOperation("Upload File")
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(file.getOriginalFilename())
                .toUriString();
        storageService.save(file, fileDownloadUri);
        return new UploadFileResponse(file.getOriginalFilename(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @ApiOperation("Get List Files")
    @GetMapping("/documents")
    public ResponseEntity<List<File>> getListFiles() {
        List<File> list = storageService.getAllTable();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @ApiOperation("Get File")
    @GetMapping("/documents/{fileId}")
    public ResponseEntity<File> getFile(@PathVariable String fileId) {
        File file = storageService.getFile(fileId);
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }
    @ApiOperation("Delete File By File Id")
    @DeleteMapping("/document/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileId) {
        storageService.deleteFile(fileId);
        return ResponseEntity.ok(new MessageResponse("successfully!"));
    }

    @ApiOperation("Update File By File Id")
    @PutMapping("/document/{fileId}")
    public ResponseEntity<?> updateFile(@RequestParam("file") MultipartFile file, @PathVariable String fileId) {
        storageService.update(file, fileId);
        return ResponseEntity.ok(new MessageResponse("successfully!"));
    }
}