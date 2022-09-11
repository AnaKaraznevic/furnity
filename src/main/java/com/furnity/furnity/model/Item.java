package com.furnity.furnity.model;

import com.furnity.furnity.enums.ItemColor;
import com.furnity.furnity.enums.ItemCondition;
import com.furnity.furnity.enums.ItemMaterial;
import com.furnity.furnity.enums.ItemStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

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

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

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

	@Lob
	@Column(name = "filename")
	private MultipartFile filename;

	@Column(name = "file")
	private String file;
}
