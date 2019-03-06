package com.sinc.project.product.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.product.dao.ProductDao;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	@Resource(name="productDao")
	private ProductDao dao;
	
	public ProductServiceImpl() {}
	
	@Override
	public Object getProducts(Object obj) {
		System.out.println("ProductServiceImpl is runnning");
		
		return dao.productsRow(obj);
	}

}
