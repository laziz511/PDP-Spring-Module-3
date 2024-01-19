package uz.pdp.online.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.springbootapplication.entity.Agent;
import uz.pdp.online.springbootapplication.entity.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByUsername(String username);

    Optional<Agent> findByEmail(String email);

    List<Agent> findByCompany(Company company);
}