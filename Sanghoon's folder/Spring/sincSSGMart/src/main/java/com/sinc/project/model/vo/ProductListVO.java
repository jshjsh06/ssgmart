package com.sinc.project.model.vo;

public class ProductListVO {
	 private String product_Id;
	 private String storeName;
	 private String productName;
	 private String category;
	 private byte[] image;
	 private int price;
	 private int discountPrice;
	 private int stock;
	 private String valid;
	 
	 public ProductListVO() {
		 super();
		 // TODO Auto-generated constructor stub
	 }
	
	 public ProductListVO(String product_Id, String storeName, String productName, String category, byte[] image, int price, int discountPrice, int stock, String valid) {
		 super();
		 this.product_Id = product_Id;
		 this.storeName = storeName;
		 this.productName = productName;
		 this.category = category;
		 this.image = image;
		 this.price = price;
		 this.discountPrice = discountPrice;
		 this.stock = stock;
		 this.valid = valid;
	 }
	 
	 public String getProduct_Id() {
		 return product_Id;
	 }
	
	 public void setProduct_Id(String product_Id) {
		 this.product_Id = product_Id;
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
	
	 public byte[] getImage() {
		 return image;
	 }
	
	 public void setImage(byte[] image) {
		 this.image = image;
	 }
	
	 public int getPrice() {
		 return price;
	 }
	
	 public void setPrice(int price) {
		 this.price = price;
	 }
	
	 public int getDiscountPrice() {
		 return discountPrice;
	 }
	
	 public void setDiscountPrice(int discountPrice) {
		 this.discountPrice = discountPrice;
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