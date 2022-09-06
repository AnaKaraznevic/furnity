package com.furnity.furnity.service;

import com.furnity.furnity.exception.ItemNotFoundException;
import com.furnity.furnity.exception.UserNotFoundException;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.model.User;
import com.furnity.furnity.repository.ItemRepository;
import com.furnity.furnity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    // Constructor
    @Autowired
    public ItemService( ItemRepository itemRepository ) {
        this.itemRepository = itemRepository;
    }

    public Item saveNewItem(Item newItem ) {    return itemRepository.save(newItem);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item findItemById(Long id){
        return itemRepository.findItemById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item by id " + id + " was not found in Data Base" ));
    }

    public List<Item> findItemByTown (String town) {
        return itemRepository.findItemsByUserAddressTown(town);
    }

    public void deleteItemById(Long id){
        itemRepository.deleteItemById(id);
    }


    public Item updateItem(Item itemInfo){
        Item item = itemRepository.findItemById(itemInfo.getId())
                .orElseThrow(() -> new ItemNotFoundException("Item by id " + itemInfo.getId() +
                        " was not found in DataBase" ));

        item.setUser(itemInfo.getUser());
        item.setName(itemInfo.getName());
        item.setDescription(itemInfo.getDescription());

        Item updatedItem = itemRepository.save(item);

        return itemRepository.save(updatedItem);
    }
}