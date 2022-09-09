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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

   // @Column(name = "user_id")
   // private User userId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "item_condition")
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;

    @Column(name = "item_color")
    @Enumerated(EnumType.STRING)
    private ItemColor itemColor;

    @Column(name = "item_material")
    @Enumerated(EnumType.STRING)
    private ItemMaterial itemMaterial;

    @Column(name = "item_style")
    @Enumerated(EnumType.STRING)
    private ItemStyle itemStyle;

    public enum ItemCondition {New, Good, Poor};
    public enum ItemColor {Black, White, Red, Blue, Green, Brown, Grey, Pink, Purple};
    public enum ItemMaterial {Glass, Metal, Wooden, Plastic}
    public enum ItemStyle {Modern, Country, Rustic, Classic}
}
