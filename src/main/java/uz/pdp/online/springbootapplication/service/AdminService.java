package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.springbootapplication.dto.AdminDTO;
import uz.pdp.online.springbootapplication.entity.Admin;
import uz.pdp.online.springbootapplication.exception.NotFoundException;
import uz.pdp.online.springbootapplication.mapper.AdminMapper;
import uz.pdp.online.springbootapplication.repository.AdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;



    public AdminDTO findAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            throw new NotFoundException("Admin not found with id: " + id);
        }
        return adminMapper.toDTO(admin.get());
    }

    public AdminDTO findAdminByUsername(String username) {
        Optional<Admin> admin = adminRepository.findByEmail(username);
        if (admin.isEmpty()) {
            throw new NotFoundException("Admin not found with username: " + username);
        }
        return adminMapper.toDTO(admin.get());
    }

    public AdminDTO findAdminByEmail(String email) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isEmpty()) {
            throw new NotFoundException("Admin not found with email: " + email);
        }
        return adminMapper.toDTO(admin.get());
    }

    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(adminMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        Admin admin = adminMapper.toEntity(adminDTO);
        Admin save = adminRepository.save(admin);
        return adminMapper.toDTO(save);
    }

    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
}
