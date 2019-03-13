package com.sinc.project.model.vo;

public class StockVO {
	private String id;
	private String productName;
	private String units;
	
	public StockVO() {}

	public StockVO(String id, String productName, String units) {
		super();
		this.id = id;
		this.productName = productName;
		this.units = units;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "StockVO [id=" + id + ", productName=" + productName + ", units=" + units + "]";
	}
	
	
	

}
