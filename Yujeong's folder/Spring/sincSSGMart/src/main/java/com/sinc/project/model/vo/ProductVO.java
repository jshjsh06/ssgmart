package com.sinc.project.model.vo;

public class ProductVO {
	private String storename, productname, category;
	private int price, stock;
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductVO(String storename, String productname, String category, int price, int stock) {
		super();
		this.storename = storename;
		this.productname = productname;
		this.category = category;
		this.price = price;
		this.stock = stock;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
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
	
	
	
}
