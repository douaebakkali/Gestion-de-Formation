package com.example.demo.Service;

import com.example.demo.entities.Content;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface contentService {
    Content storeFile(MultipartFile file, String contentType) throws IOException;

    Content shareContent(Long contentId);

    byte[] downloadContent(Long contentId) throws IOException;

    Content getFile(Long id);
}