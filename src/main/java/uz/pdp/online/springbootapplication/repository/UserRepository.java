package uz.pdp.online.springbootapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.springbootapplication.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
