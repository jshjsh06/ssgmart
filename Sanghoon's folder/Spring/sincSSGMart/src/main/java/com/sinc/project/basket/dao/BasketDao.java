package com.sinc.project.basket.dao;

import java.util.List;
import java.util.Map;

public interface BasketDao {

	public void insertMyBasketRow(Object obj);
	public List<Object> selectMyBasketRow(String username);
<<<<<<< HEAD
	//public List<Object> selectMyBasketRow(Object obj);
=======
>>>>>>> 0121a1eab31d1b40898c81fed75314f38dc5d816
	public void deleteMyBasketRow(Object obj);
	public List<Object> barcodeBasketRow(String barcode);
	public void updateBasketRow(List<Object> obj);
	
}
