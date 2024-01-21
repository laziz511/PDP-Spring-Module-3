package uz.pdp.online.springbootapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.entity.Item;
import uz.pdp.online.springbootapplication.service.ItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/item/*")
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(value = "create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Item> create(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping(value = "update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Item> update(@RequestBody Item item) {
        Item updatedItem = itemService.updateItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @PostMapping(value = "delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>("Successfully Deleted - Item", HttpStatus.NO_CONTENT);
    }
}
