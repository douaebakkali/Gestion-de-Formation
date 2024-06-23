package com.example.demo.Service;

import com.example.demo.dto.AdminDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<AdminDTO> getAllAdmins();
    Optional<AdminDTO> getAdminById(Long id);
    AdminDTO saveAdmin(AdminDTO adminDTO);
    void deleteAdmin(Long id);
    boolean checkForAdminLogin(String email, String password);
}

