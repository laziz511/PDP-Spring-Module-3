package uz.pdp.online.springbootapplication.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.springbootapplication.entity.Store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public Optional<Store> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Store.class, id));
    }

    public List<Store> findAll() {
        return entityManager.createQuery("SELECT s FROM Store s", Store.class).getResultList();
    }

    @Transactional
    public Store save(Store store) {
        if (store.getId() == null) {
            entityManager.persist(store);
        } else {
            entityManager.merge(store);
        }
        return store;
    }

    @Transactional
    public void delete(Store store) {
        entityManager.remove(store);
    }
}
