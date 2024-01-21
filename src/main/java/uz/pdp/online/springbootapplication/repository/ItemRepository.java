package uz.pdp.online.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.springbootapplication.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}