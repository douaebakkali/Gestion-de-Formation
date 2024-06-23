package com.example.demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class testController {

    @GetMapping("/public")
    public String publicAccess() {
        return "Public Content.";
    }

    @GetMapping("/participant")
    @PreAuthorize("hasRole('PARTICIPANT')")
    public String participantAccess() {
        return "Participant Content.";
    }

    @GetMapping("/formateur")
    @PreAuthorize("hasRole('FORMATEUR')")
    public String formateurAccess() {
        return "Formateur Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Content.";
    }
}
