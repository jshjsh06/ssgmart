package com.sinc.project.model.vo;

public class BasketVO {
	
	private String	productName;
	private String	order_Id;
	private String	basket_Id;
	private int 	count;
	private int 	basket_Price;
	
	public BasketVO(String productName, String order_Id, String basket_Id, int count, int basket_Price) {
		super();
		this.productName = productName;
		this.order_Id = order_Id;
		this.basket_Id = basket_Id;
		this.count = count;
		this.basket_Price = basket_Price;
	}
	public BasketVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	
	@Override
	public String toString() {
		return "BasketVO [productName=" + productName + ", order_Id=" + order_Id + ", basket_Id=" + basket_Id
				+ ", count=" + count + ", basket_Price=" + basket_Price + "]";
	}
	
	
	
	
}
