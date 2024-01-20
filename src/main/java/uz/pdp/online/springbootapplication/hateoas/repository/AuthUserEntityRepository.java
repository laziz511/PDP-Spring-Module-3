package uz.pdp.online.springbootapplication.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.springbootapplication.hateoas.entity.AuthUserEntity;

public interface AuthUserEntityRepository extends JpaRepository<AuthUserEntity, Long> {
}