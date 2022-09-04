package com.furnity.furnity.service;


import com.furnity.furnity.exception.ItemNotFoundException;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


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

    public Item getItemById(Long id){
        Optional<Item> optional = itemRepository.findById(id);
        Item item = null ;
        if(optional.isPresent()){
            item = optional.get();
        } else {
            throw new ItemNotFoundException("Item not found for id :: " + id);
        }
        return item;
    }
}
