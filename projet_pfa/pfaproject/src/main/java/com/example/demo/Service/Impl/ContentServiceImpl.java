package com.example.demo.Service.Impl;

import com.example.demo.Repository.ContentRepository;
import com.example.demo.Service.contentService;
import com.example.demo.entities.Content;
import com.example.demo.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class ContentServiceImpl implements contentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Content storeFile(MultipartFile file, String contentType) throws IOException {
        // Validation du type de fichier et gestion du stockage
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "C:\\Users\\Lenovo\\Desktop\\PDFS\\" + fileName;
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        Content content = new Content();
        content.setContentType(contentType);
        content.setFileName(fileName);
        content.setFilePath(filePath);

        return contentRepository.save(content);
    }

    @Override
    public Content shareContent(Long contentId) {
        Optional<Content> optionalContent = contentRepository.findById(contentId);
        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();
            // Implémentez la logique de partage du contenu ici (par exemple, mettre à jour une propriété shared ou gérer les permissions)
            content.setShared(true); // Exemple simple : marquer le contenu comme partagé
            return contentRepository.save(content);
        } else {
            throw new UserNotFoundException("Content not found with id: " + contentId);
        }
    }
    @Override
    public byte[] downloadContent(Long contentId) throws IOException {
        Content fetchedImage = contentRepository.findById(contentId).orElseThrow(() -> new EntityNotFoundException("Image with given imageID" + contentId + "not found."));
        try {
            return Files.readAllBytes(new File(fetchedImage.getFilePath()).toPath());
        } catch (IOException e) {
            throw new IOException("Failed to read image file: " + fetchedImage.getFilePath(), e);
        }
    }

    @Override
    public Content getFile(Long id) {
        Optional<Content> content=contentRepository.findById(id);
        if (content.isPresent()) {
            Content con=content.get();
            return con;
        }
        else throw new UserNotFoundException("not found");
    }


    // Ajoutez d'autres méthodes pour récupérer, mettre à jour et supprimer des fichiers
}