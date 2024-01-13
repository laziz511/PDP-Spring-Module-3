package uz.pdp.online.springbootapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.entity.Store;
import uz.pdp.online.springbootapplication.exception.ResourceNotFoundException;
import uz.pdp.online.springbootapplication.repository.StoreRepository;
import uz.pdp.online.springbootapplication.service.StoreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + storeId));
    }

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Long storeId, Store store) {
        Store existingStore = getStoreById(storeId);
        existingStore.setName(store.getName());
        existingStore.setDescription(store.getDescription());
        return storeRepository.save(existingStore);
    }

    public void deleteStore(Long storeId) {
        Store store = getStoreById(storeId);
        storeRepository.delete(store);
    }
}
