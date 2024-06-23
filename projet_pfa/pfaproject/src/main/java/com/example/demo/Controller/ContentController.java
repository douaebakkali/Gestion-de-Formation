package com.example.demo.Controller;

import com.example.demo.Service.Impl.ContentServiceImpl;
import com.example.demo.entities.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentServiceImpl contentService;

    @PostMapping("/upload")
    public ResponseEntity<Content> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("contentType") String contentType) {
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("Content type: " + contentType);
            Content storedContent = contentService.storeFile(file, contentType);
            return new ResponseEntity<>(storedContent, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("{contentId}")
    public ResponseEntity<?> downloadContent(@PathVariable("contentId") Long contentId) throws IOException {
        byte[] imageData = contentService.downloadContent(contentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageData);
    }
}





