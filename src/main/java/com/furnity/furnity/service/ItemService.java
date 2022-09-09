package com.furnity.furnity.service;

import com.furnity.furnity.exception.ItemNotFoundException;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public void deleteItem(Long id) {
        this.itemRepository.deleteById(id);
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item by id " + id + " was not found"));
    }
    public Item updateItem( Long id, Item request ) {
        Optional<Item> fromDB = itemRepository.findById(id);
        if (fromDB.isPresent()) {
            Item item = fromDB.get();
            item.setCategory(request.getCategory());
            item.setName(request.getName());
            item.setPrice(request.getPrice());
            item.setDescription(request.getDescription());
            item.setItemCondition(request.getItemCondition());
            return itemRepository.save(item);
        } else {
            throw new ItemNotFoundException("Item with ID :: " + id + " not found in DB");
        }
    }

    public List<Item> findItemsByKeyword(String keyword){
        return itemRepository.findByKeyword(keyword);
    }
}
