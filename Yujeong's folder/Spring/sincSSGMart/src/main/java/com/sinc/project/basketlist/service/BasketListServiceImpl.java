package com.sinc.project.basketlist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.basketlist.dao.BasketListDao;

@Service("basketListService")
public class BasketListServiceImpl implements BasketListService {

	@Resource(name = "basketListDao")
	private BasketListDao dao;	
	
	public List<Object> basketList_list(Object obj) {
		System.out.println("------ 장바구니 service");
		return dao.basketList_listRow(obj);
	}
	
}
