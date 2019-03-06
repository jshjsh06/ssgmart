package com.sinc.project.model.vo;

public class StoreVO {
	private String id;
	private String name;
	private String address;
	
	public StoreVO() {}
	
	public StoreVO(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StoreVO [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	
}
