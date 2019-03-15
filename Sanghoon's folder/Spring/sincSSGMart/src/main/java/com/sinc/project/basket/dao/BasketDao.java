package com.sinc.project.basket.dao;

import java.util.List;
import java.util.Map;

public interface BasketDao {

	public void insertMyBasketRow(Object obj);
	public List<Object> selectMyBasketRow(String username);
	//public List<Object> selectMyBasketRow(Object obj);
	public void deleteMyBasketRow(Object obj);
	public List<Object> barcodeBasketRow(String barcode);
	public void updateBasketRow(List<Object> obj);
	
}
