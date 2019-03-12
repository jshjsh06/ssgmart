package com.sinc.project.model.vo;

public class ProductListVO {

	private String storeName, productName, category;
	private int price, stock;
	private String valid;
	
	public ProductListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductListVO(String storeName, String productName, String category, int price, int stock, String valid) {
		super();
		this.storeName = storeName;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.valid = valid;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	

	
}
