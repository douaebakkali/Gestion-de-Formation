package com.example.demo.Service.Impl;

import com.example.demo.Mapper.AdminMapper;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.ParticipantRepository;
import com.example.demo.Service.AdminService;
import com.example.demo.dto.AdminDTO;
import com.example.demo.entities.Admin;
import com.example.demo.exception.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ParticipantRepository participantRepository;


    @Override
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(adminMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdminDTO> getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(adminMapper::toDTO);
    }

    @Override
    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        Optional<Admin> existingAdmin = adminRepository.findByEmail(adminDTO.getEmail());

        existingAdmin.ifPresent(admin -> {
            throw new UserFoundException("Admin with email: " + admin.getEmail() + " already exists.");
        });

        Admin admin = adminMapper.toEntity(adminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.toDTO(savedAdmin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public boolean checkForAdminLogin(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        return adminOpt.isPresent() && adminOpt.get().getPassword().equals(password);
    }

}
