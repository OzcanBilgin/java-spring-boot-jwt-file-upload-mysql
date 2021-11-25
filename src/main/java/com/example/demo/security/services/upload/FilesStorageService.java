package com.example.demo.security.services.upload;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.example.demo.models.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public File save(MultipartFile file, String uri);

    public void deleteAll();

    public List<File> getAllTable();

    public File getFile(String fileId);

    public void deleteFile(String fileId);

    public void update(MultipartFile file, String fileId);
}