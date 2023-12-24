package uz.pdp.online.springbootapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.dto.ItemCreateDTO;
import uz.pdp.online.springbootapplication.entity.Item;
import uz.pdp.online.springbootapplication.exception.ResourceNotFoundException;
import uz.pdp.online.springbootapplication.mapper.ItemMapper;
import uz.pdp.online.springbootapplication.repository.ItemRepository;
import uz.pdp.online.springbootapplication.service.ItemService;

import java.util.List;

import static uz.pdp.online.springbootapplication.utils.Utils.deleteImageFromFileSystem;
import static uz.pdp.online.springbootapplication.utils.Utils.saveImage;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
    }

    public Item createItem(ItemCreateDTO dto) {
        Item item = itemMapper.toEntity(dto);
        String path = saveImage(dto.getImage());
        item.setPath(path);

        return itemRepository.save(item);
    }

    public Item updateItem(Long itemId, ItemCreateDTO dto) {

        Item existingItem = getItemById(itemId);
        updateItemFields(dto, existingItem);

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            deleteImageFromFileSystem(existingItem.getPath());

            String path = saveImage(dto.getImage());
            existingItem.setPath(path);
        }

        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long itemId) {
        Item item = getItemById(itemId);
        itemRepository.delete(item);
    }

    private static void updateItemFields(ItemCreateDTO dto, Item existingItem) {
        existingItem.setName(dto.getName());
        existingItem.setDescription(dto.getDescription());
        existingItem.setPrice(dto.getPrice());
    }
}
