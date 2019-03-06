package com.sinc.project.model.vo;

public class ProductVO {
	private String id;
	private String productName;
	private String category;
	
	public ProductVO(String id, String productName, String category) {
		super();
		this.id = id;
		this.productName = productName;
		this.category = category;
	}

	public ProductVO() {}

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

	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", productName=" + productName + ", category=" + category + "]";
	}
	
	
}
