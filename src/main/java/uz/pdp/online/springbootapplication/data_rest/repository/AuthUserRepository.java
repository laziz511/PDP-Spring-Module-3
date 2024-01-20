package uz.pdp.online.springbootapplication.data_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.springbootapplication.data_rest.entity.AuthUser;


@RepositoryRestResource(path = "auth-user", collectionResourceRel = "auth-user")
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

}