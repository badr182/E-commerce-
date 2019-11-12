package org.sid.eco.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor 
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String descreption;
	private double currentprice;
	private boolean promoion;
	private boolean selected;
	private boolean available;
	private String photoName;
	
	@ManyToOne
	private Category category;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Long id, String name, String descreption, double currentprice, boolean promoion, boolean selected,
			boolean available, String photoName, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.descreption = descreption;
		this.currentprice = currentprice;
		this.promoion = promoion;
		this.selected = selected;
		this.available = available;
		this.photoName = photoName;
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescreption() {
		return descreption;
	}
	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}
	public double getCurrentprice() {
		return currentprice;
	}
	public void setCurrentprice(double currentprice) {
		this.currentprice = currentprice;
	}
	public boolean isPromoion() {
		return promoion;
	}
	public void setPromoion(boolean promoion) {
		this.promoion = promoion;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
