package uz.pdp.online.springbootapplication.service;

import uz.pdp.online.springbootapplication.entity.Store;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();
    Store getStoreById(Long storeId);
    Store createStore(Store store);
    Store updateStore(Long storeId, Store store);
    void deleteStore(Long storeId);
}
