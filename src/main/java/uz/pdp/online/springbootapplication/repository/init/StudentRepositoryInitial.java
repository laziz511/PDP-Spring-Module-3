package uz.pdp.online.springbootapplication.repository.init;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.springbootapplication.springdatajpa.StudentInit;

public interface StudentRepositoryInitial extends JpaRepository<StudentInit, Long> {
}
