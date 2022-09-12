package com.furnity.furnity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	//(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID", insertable = false, updatable = false)
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

	public enum ItemCondition {
		New, Good, Poor
	};

	public enum ItemColor {
		Black, White, Red, Blue, Green, Brown, Grey, Pink, Purple
	};

	public enum ItemMaterial {
		Glass, Metal, Wooden, Plastic
	}

	public enum ItemStyle {
		Modern, Country, Rustic, Classic
	}

	@Lob
	@Column(name = "filename")
	private MultipartFile filename;

	@Column(name = "file")
	private String file;
}
