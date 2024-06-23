package com.example.demo.Mapper;

import com.example.demo.dto.AdminDTO;
import com.example.demo.entities.Admin;
import com.example.demo.entities.ERole;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDTO toDTO(Admin admin) {
        if (admin == null) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setUsername(admin.getUsername());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setNom(admin.getNom());
        adminDTO.setPrenom(admin.getPrenom());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setRole(admin.getRole().name());

        return adminDTO;
    }

    public static Admin toEntity(AdminDTO adminDTO) {
        if (adminDTO == null) {
            return null;
        }

        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        admin.setNom(adminDTO.getNom());
        admin.setPrenom(adminDTO.getPrenom());
        admin.setEmail(adminDTO.getEmail());
        admin.setRole(ERole.valueOf(adminDTO.getRole()));

        return admin;
    }
}
