package com.sinc.project.model.vo;

import java.util.Arrays;

public class MyProductListVO {
	 private String product_Id;
	 private String storeName;
	 private String productName;
	 private String category;
	 private byte[] image;
	 private int price;
	 private int discountPrice;
	 private int stock;
	 private String valid;
	  
	 private String user_Id;
	 private int cnt;
	 
	 public MyProductListVO() {}

	public MyProductListVO(String product_Id, String storeName, String productName, String category, byte[] image,
			int price, int discountPrice, int stock, String valid, String user_Id, int cnt) {
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
		this.user_Id = user_Id;
		this.cnt = cnt;
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

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "MyProductListVO [product_Id=" + product_Id + ", storeName=" + storeName + ", productName=" + productName
				+ ", category=" + category + ", image=" + Arrays.toString(image) + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", stock=" + stock + ", valid=" + valid + ", user_Id=" + user_Id
				+ ", cnt=" + cnt + "]";
	}
	
 
}