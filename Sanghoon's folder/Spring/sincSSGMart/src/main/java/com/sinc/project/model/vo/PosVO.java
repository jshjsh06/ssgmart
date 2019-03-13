package com.sinc.project.model.vo;

public class PosVO {
	private String name;
	private int count;
	private int priceSell;
	
	public PosVO() {}
	
	public PosVO(String name, int count, int priceSell) {
		super();
		this.name = name;
		this.count = count;
		this.priceSell = priceSell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(int priceSell) {
		this.priceSell = priceSell;
	}

	@Override
	public String toString() {
		return "PosVO [name=" + name + ", count=" + count + ", priceSell=" + priceSell + "]";
	}
}
