package com.sinc.project.model.vo;

public class PosVO {
	private String name;
	private int cnt;
	private int priceSell;
	
	public PosVO() {}
	
	public PosVO(String name, int cnt, int priceSell) {
		super();
		this.name = name;
		this.cnt = cnt;
		this.priceSell = priceSell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(int priceSell) {
		this.priceSell = priceSell;
	}

	@Override
	public String toString() {
		return "PosVO [name=" + name + ", cnt=" + cnt + ", priceSell=" + priceSell + "]";
	}
}
