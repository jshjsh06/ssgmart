package com.sinc.project.basket.service;

import java.util.List;
import java.util.Map;

public interface BasketService {
	public void insertMyBasket(Object obj);
	public List<Object> selectMyBasket(String username);

	public List<Object> deleteMyBasket(Object obj);

	public List<Object> getBasketByBarcode(String barcode);
}
