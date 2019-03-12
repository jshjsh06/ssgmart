package com.sinc.project.productlist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.model.vo.ProductListVO;
import com.sinc.project.productlist.dao.ProductListDao;

@Service("productListService")
public class ProductListServiceImpl implements ProductListService {

	@Resource(name = "productListDao")
	private ProductListDao dao;	
	@Override
	public List<Object> productList_list() {
		System.out.println("pListServiceImpl pList_list");
		return dao.productList_listRow();
	}
	
	public List<Object> productList_list1(Object obj) {

		System.out.println("매장 불러오는 service");
		
		return dao.productList_listRow1(obj);
	}
	
	public List<Object> productList_list2(Object obj) {

		System.out.println("매장 + 카테고리 service");
		
		return dao.productList_listRow2(obj);
	}
	
}
