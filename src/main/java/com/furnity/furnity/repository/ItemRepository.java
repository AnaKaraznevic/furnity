package com.furnity.furnity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furnity.furnity.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
