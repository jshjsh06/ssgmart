package com.sinc.project.basket.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.product.dao.BasketDao;

@Service("basketService")
public class BasketServiceImpl implements BasketService{

	@Resource(name = "basketDao")
	private BasketDao dao;
	
	public BasketServiceImpl() {}
	
	@Override
	public Object getBasket(Object obj) {
		System.out.println("BasketServiceImpl is running");
		return dao.BasketRow(obj);
	}

	@Override
	public List<Object> getBasket2(Object obj) {
		System.out.println("BasketServiceImpl2 is running");
		return dao.BasketRow2(obj);
	}

}
