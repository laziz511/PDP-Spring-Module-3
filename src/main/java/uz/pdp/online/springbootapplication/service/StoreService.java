package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.entity.Store;
import uz.pdp.online.springbootapplication.exception.ResourceNotFoundException;
import uz.pdp.online.springbootapplication.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store", "id", id));
    }

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Long id, Store updatedStore) {
        Store existingStore = getStoreById(id);

        BeanUtils.copyProperties(updatedStore, existingStore, "id");

        return storeRepository.save(existingStore);
    }

    public void deleteStore(Long id) {
        Store existingStore = getStoreById(id);
        storeRepository.delete(existingStore);
    }

}
