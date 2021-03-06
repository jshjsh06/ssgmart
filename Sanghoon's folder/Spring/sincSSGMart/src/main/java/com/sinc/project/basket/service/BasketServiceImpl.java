package com.sinc.project.basket.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.basket.dao.BasketDao;

@Service("basketService")
public class BasketServiceImpl implements BasketService{

	@Resource(name = "basketDao")
	private BasketDao dao;
	
	public BasketServiceImpl() {}
	
	@Override
	public void insertMyBasket(Object obj) {
		System.out.println("insertMyBasket Impl is running");
		dao.insertMyBasketRow(obj);
//		return dao.insertMyBasketRow(obj);
	}
	
	@Override
	public List<Object> selectMyBasket(String username) {
		System.out.println("insertMyBasket Impl is running");
		
		return dao.selectMyBasketRow(username);
	}
	
	@Override
	public void deleteMyBasket(Object obj) {
		System.out.println("deleteMyBasket Impl is running");
		
		dao.deleteMyBasketRow(obj);
	}
	
	@Override
	public void updateBasket(List<Object> obj) {
		System.out.println("updateBasket Impl is running");
		
		dao.updateBasketRow(obj);
	}
	

	@Override
	public List<Object> getBasketByBarcode(String user_Id) {
		System.out.println("getBasketByBarcode is running");
		return dao.barcodeBasketRow(user_Id);
	}

}
