package com.src.cartitem;

import org.springframework.stereotype.Component;
import com.src.cartitem.cartItemsQual;

@Component
public class CartItems {
	int id;
	String brand;
	String model;

	public CartItems() {}
	public CartItems(int id, String brand, String model) {
		this.id=id;
		this.brand=brand;
		this.model=model;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "CartItems [id= " + id + ", brand= " + brand + ", model= " + model + "]";
	}	
}
