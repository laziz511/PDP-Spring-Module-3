package uz.pdp.online.springbootapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.entity.Store;
import uz.pdp.online.springbootapplication.service.StoreService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/store/*")
public class StoreController {

    private final StoreService storeService;

    @GetMapping(value = "get/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Store> get(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @PostMapping(value = "create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Store> create( @RequestBody Store entity) {
        Store createdStore = storeService.createStore(entity);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @PutMapping(value = "update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Store> update( @RequestBody Store entity) {
        Store updatedStore = storeService.updateStore(entity);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>("Successfully Deleted - Store", HttpStatus.NO_CONTENT);
    }
}
