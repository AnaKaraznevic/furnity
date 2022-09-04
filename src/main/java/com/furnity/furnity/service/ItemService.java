package com.furnity.furnity.service;


import com.furnity.furnity.model.Item;
import com.furnity.furnity.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ItemService {

    @Autowired
    private final ItemRepository itemRepository;

    public ItemService( ItemRepository itemRepository ) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item){ return itemRepository.save(item); }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public void deleteItem(Long id){
        this.itemRepository.deleteById(id);
    }
}
