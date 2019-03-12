package com.sinc.project.cart.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.cart.dao.CartDao;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Resource(name = "cartDao")
	private CartDao dao;
	
	@Override
	public List<Object> cartCartLists() {
		System.out.println("CartServiceImpl carCartLists");
		return dao.cartListsRow();
	}
	
	public void cartDelete(int cart_id) {
		System.out.println("CartDelete cartDelete");
		dao.cartDelete(cart_id);
	}
	
	
}
