package uz.pdp.online.springbootapplication.service;

import uz.pdp.online.springbootapplication.dto.ItemCreateDTO;
import uz.pdp.online.springbootapplication.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();
    Item getItemById(Long itemId);
    Item createItem(ItemCreateDTO dto);
    Item updateItem(Long itemId, ItemCreateDTO dto);
    void deleteItem(Long itemId);
}
