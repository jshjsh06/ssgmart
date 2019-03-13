package com.sinc.project.model.vo;

public class ProductListVO {

	 private String storeName, productName, category;
	 private int price, stock;
	 public ProductListVO() {
	  super();
	  // TODO Auto-generated constructor stub
	 }
	 public ProductListVO(String storeName, String productName, String category, int price, int stock) {
	  super();
	  this.storeName = storeName;
	  this.productName = productName;
	  this.category = category;
	  this.price = price;
	  this.stock = stock;
	 }
	 public String getStorename() {
	  return storeName;
	 }
	 public void setStorename(String storename) {
	  this.storeName = storename;
	 }
	 public String getProductname() {
	  return productName;
	 }
	 public void setProductname(String productname) {
	  this.productName = productname;
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


