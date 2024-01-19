package uz.pdp.online.springbootapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.dto.AdminDTO;
import uz.pdp.online.springbootapplication.dto.AgentDTO;
import uz.pdp.online.springbootapplication.dto.CityDTO;
import uz.pdp.online.springbootapplication.dto.CompanyDTO;
import uz.pdp.online.springbootapplication.service.AdminService;
import uz.pdp.online.springbootapplication.service.AgentService;
import uz.pdp.online.springbootapplication.service.CityService;
import uz.pdp.online.springbootapplication.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AgentService agentService;
    private final CityService cityService;
    private final CompanyService companyService;

    @PostMapping("/agents")
    public ResponseEntity<Void> addAgent(@RequestBody AgentDTO agentDTO) {
        agentService.saveAgent(agentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/agents/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgentById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cities")
    public ResponseEntity<Void> addCity(@RequestBody CityDTO cityDTO) {
        cityService.addCity(cityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCityById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/companies")
    public ResponseEntity<Void> addCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.addCompany(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        AdminDTO admin = adminService.findAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/admins")
    public ResponseEntity<Void> addAdmin(@RequestBody AdminDTO adminDTO) {
        adminService.saveAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}