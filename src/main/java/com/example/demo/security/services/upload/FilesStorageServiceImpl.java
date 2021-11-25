package com.example.demo.security.services.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.File;
import com.example.demo.repository.FileRepository;
import com.example.demo.exception.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("uploads");
    @Autowired
    private FileRepository fileRepository;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public File save(MultipartFile file, String uri) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if ((fileName.contains(".png")) || (fileName.contains(".jpeg")) || (fileName.contains(".jpg")) || (fileName.contains(".docx")) || (fileName.contains(".pdf")) || (fileName.contains(".xlsx"))) {
                File modelFile = new File(fileName, file.getContentType(), uri);
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                return fileRepository.save(modelFile);
            } else throw new StorageException("Sorry! Filename contains invalid path sequenth " + fileName);

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public List<File> getAllTable() {
        return fileRepository.findAll();
    }

    @Override
    public File getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException("File not found with id " + fileId));
    }

    @Override
    public void deleteFile(String fileId) {
        fileRepository.deleteById(fileId);
    }

    @Override
    public void update(MultipartFile file, String fileId) {
        fileRepository.findById(fileId)
                .map(value -> {
                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/files/")
                            .path(file.getOriginalFilename())
                            .toUriString();
                    value.setFileName(file.getOriginalFilename());
                    value.setFileType(file.getContentType());
                    value.setUrl(fileDownloadUri);
                    try {
                        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                    } catch (IOException e) {
                        throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
                    }
                    return fileRepository.save(value);
                }).orElseThrow(() -> new NotFoundException("File not found with id " + fileId));
    }
}