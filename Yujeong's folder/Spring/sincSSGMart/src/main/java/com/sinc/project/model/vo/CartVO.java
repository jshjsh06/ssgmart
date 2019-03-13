package com.sinc.project.model.vo;

public class CartVO {	
	private int		cart_id, product_id, amount, product_price;
	private String	product_name;
	public CartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartVO(int cart_id, int product_id, int amount, int product_price, String product_name) {
		super();
		this.cart_id = cart_id;
		this.product_id = product_id;
		this.amount = amount;
		this.product_price = product_price;
		this.product_name = product_name;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	

	
	
}
