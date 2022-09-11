package com.furnity.furnity.repository;

import com.furnity.furnity.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select * from Item s where s.name like %:keyword% or s.description like %:keyword% " +
            "or s.item_condition like %:keyword% or s.item_color like %:keyword% or s.item_material like %:keyword% " +
            "or s.item_style like %:keyword%", nativeQuery = true)
    List<Item> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from Item s where (s.name like %:keyword% or s.description like %:keyword% " +
            "or s.item_condition like %:keyword% or s.item_color like %:keyword% or s.item_material like %:keyword% " +
            "or s.item_style like %:keyword%) and s.user_id = :userId", nativeQuery = true)
    List<Item> findByKeywordAndUserId(@Param("keyword") String keyword, @Param("userId") Long userId);

    List<Item> findItemsByUserId (Long userId);
}
