package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.entity.Item;
import uz.pdp.online.springbootapplication.exception.ResourceNotFoundException;
import uz.pdp.online.springbootapplication.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem = getItemById(id);

        BeanUtils.copyProperties(updatedItem, existingItem, "id");

        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        Item existingItem = getItemById(id);
        itemRepository.delete(existingItem);
    }
}
