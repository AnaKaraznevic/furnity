package com.furnity.furnity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @Column(name = "user_id")
    private User userId;

    @Column(name = "name")
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;


    @Column(name = "item_condition")
    private String itemCondition;

}
