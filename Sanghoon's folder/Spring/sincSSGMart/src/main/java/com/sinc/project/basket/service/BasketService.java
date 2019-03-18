package com.sinc.project.basket.service;

import java.util.List;
import java.util.Map;

public interface BasketService {
	public void insertMyBasket(Object obj);
	public List<Object> selectMyBasket(String username);
<<<<<<< HEAD
	//public List<Object> selectMyBasket(Object obj);
	public void deleteMyBasket(Object obj);
=======

	public void deleteMyBasket(Object obj);

>>>>>>> 0121a1eab31d1b40898c81fed75314f38dc5d816
	public List<Object> getBasketByBarcode(String barcode);
	public void updateBasket(List<Object> obj);
}
