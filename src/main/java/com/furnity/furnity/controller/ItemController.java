package com.furnity.furnity.controller;

import com.furnity.furnity.model.Item;
import com.furnity.furnity.model.User;
import com.furnity.furnity.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")  // ???
@RequestMapping("/api/v1/")
public class ItemController {

    private final ItemService itemService;

    // Constructor
    public ItemController( ItemService itemService ) {
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> allItems = itemService.findAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id){
        Item itemToFindById = itemService.findItemById(id);
        return new ResponseEntity<>(itemToFindById, HttpStatus.OK);
    }

    @GetMapping("/item/{town}")
    public ResponseEntity<List<Item>> getItemByTown(@PathVariable("town") String town){
        List <Item> itemToFindByTown = itemService.findItemByTown(town);
        return new ResponseEntity<>(itemToFindByTown, HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Item> addNewItem(@RequestBody Item item){
        Item newItem = itemService.saveNewItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> deleteItemById( @PathVariable("id") Long id){
        itemService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/item/")
    public ResponseEntity<Item> updateItem(@RequestBody Item itemInfo){
        Item updateItem = itemService.updateItem(itemInfo);
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }
}

