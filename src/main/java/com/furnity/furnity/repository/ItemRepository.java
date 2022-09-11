package com.furnity.furnity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.furnity.furnity.model.Item;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select * from Item s where s.name like %:keyword% or s.description like %:keyword% " +
            "or s.item_condition like %:keyword% or s.item_color like %:keyword% or s.item_material like %:keyword% " +
            "or s.item_style like %:keyword%", nativeQuery = true)
    List<Item> findByKeyword(@Param("keyword") String keyword);
    
    @Query(value = "select * from Item s where s.category_id = :category", nativeQuery = true)    
    List<Item> findByCategory(@Param("category") int category);
    
    @Query(value = "select * from Item s order by id desc limit :itemNumber", nativeQuery = true)   
    List<Item> findLatestItems(@Param("itemNumber") int itemNumber);
}
