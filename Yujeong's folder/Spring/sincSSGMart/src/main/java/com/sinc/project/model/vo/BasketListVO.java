package com.sinc.project.model.vo;

import java.util.Arrays;

public class BasketListVO {
	
	private String	product_Id;
	private String	order_Id;
	private String	basket_Id;
	private int 	count;
	private int 	basket_Price;
	//////////////////////////////////////
	private String storeName;
	private String productName;
	private String category;
	private byte[] image;
	private int price;
	private int discountPrice;
	private int stock;
	private String valid;
	
	public BasketListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BasketListVO(String product_Id, String order_Id, String basket_Id, int count, int basket_Price,
			String storeName, String productName, String category, byte[] image, int price, int discountPrice,
			int stock, String valid) {
		super();
		this.product_Id = product_Id;
		this.order_Id = order_Id;
		this.basket_Id = basket_Id;
		this.count = count;
		this.basket_Price = basket_Price;
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

	public String getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(String order_Id) {
		this.order_Id = order_Id;
	}

	public String getBasket_Id() {
		return basket_Id;
	}

	public void setBasket_Id(String basket_Id) {
		this.basket_Id = basket_Id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getBasket_Price() {
		return basket_Price;
	}

	public void setBasket_Price(int basket_Price) {
		this.basket_Price = basket_Price;
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

	@Override
	public String toString() {
		return "BasketListVO [product_Id=" + product_Id + ", order_Id=" + order_Id + ", basket_Id=" + basket_Id
				+ ", count=" + count + ", basket_Price=" + basket_Price + ", storeName=" + storeName + ", productName="
				+ productName + ", category=" + category + ", image=" + Arrays.toString(image) + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", stock=" + stock + ", valid=" + valid + "]";
	}
	

}
