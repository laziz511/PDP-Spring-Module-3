package uz.pdp.online.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.springbootapplication.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}