package com.sinc.project.basketdelete.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.basketdelete.dao.basketDeleteDao;

@Service ("basketDeleteService")
public class basketDeleteServiceImpl implements basketDeleteService{

	@Resource(name = "basketDeleteDao")
	private basketDeleteDao dao;
	
	public basketDeleteServiceImpl() {}
	
	@Override
	public Object basketDelete(Object obj) {
		System.out.println("basketDeleteServiceImpl is running");
		return dao.basketDeleteRow(obj);
	}

	@Override
	public List<Object> basketDelete2(Object obj) {
		System.out.println("basketDeleteServiceImpl2 is running");
		return dao.basketDeleteRow2(obj);
	}

}
