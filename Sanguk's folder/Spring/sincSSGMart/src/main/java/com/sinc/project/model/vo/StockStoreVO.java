package com.sinc.project.model.vo;

public class StockStoreVO {
	private String id;
	private String address;
	private String productName;
	private int units;
	
	public StockStoreVO() {}

	public StockStoreVO(String id, String address, String productName, int units) {
		super();
		this.id = id;
		this.address = address;
		this.productName = productName;
		this.units = units;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "StockStoreVO [id=" + id + ", address=" + address + ", productName=" + productName + ", units=" + units
				+ "]";
	}
	
	
}
