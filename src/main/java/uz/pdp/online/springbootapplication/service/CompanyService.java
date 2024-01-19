package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.springbootapplication.dto.CompanyDTO;
import uz.pdp.online.springbootapplication.entity.Company;
import uz.pdp.online.springbootapplication.mapper.CompanyMapper;
import uz.pdp.online.springbootapplication.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.map(companyMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));
    }

    public void addCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        companyRepository.save(company);
        System.out.println("Added company: " + company);
    }

    public void deleteCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            companyRepository.deleteById(id);
            System.out.println("Company deleted with ID: " + id);
        } else {
            throw new RuntimeException("Company not found with ID: " + id);
        }
    }

}