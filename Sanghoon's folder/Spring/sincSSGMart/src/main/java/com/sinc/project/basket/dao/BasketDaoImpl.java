package com.sinc.project.basket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sinc.project.model.vo.BasketVO;


@Repository("basketDao")
public class BasketDaoImpl implements BasketDao{
	
	@Resource(name = "sqlSession")
	private SqlSession session;

	private BasketVO basketVo;
	
	@Override
	public void insertMyBasketRow(Object obj) { 
		System.out.println("insertMyBasketRow is running");
		System.out.println(obj);
		
		// MYBASKET Table에 INSERT 하는 구문
		session.insert("com.sinc.mybatis.basket.insertMyBasketRow", obj);
		System.out.println("@@@@@@Insert 완료@@@@@@@");
		
		
//		Object obj2 = session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", obj);
//		System.out.println((ArrayList) obj2 + "@@@@@@@@@@@@@@@@");
//		
		// MYBASKET Table에서 나의 ID를 검색해서 SELECT 구문
//		return session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", obj);
//		return null;
	}
	
	@Override
	public List<Object> selectMyBasketRow(String user_Id) { 
		System.out.println("selectMyBasketRow is running");		
		
		Object obj2 = session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", user_Id);
		System.out.println((ArrayList) obj2 + "@@@@@@@@@@@@@@@@");
		
		// MYBASKET Table에서 나의 ID를 검색해서 SELECT 구문
		return session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", user_Id);
	}
	
	@Override
	public List<Object> deleteMyBasketRow(Object obj) {
		System.out.println("deleteMyBasketRow is running");
		System.out.println(obj);
		
		// MYBASKET Table에 INSERT 하는 구문
		session.insert("com.sinc.mybatis.basket.deleteMyBaksetRow", obj);
//		Object obj2 = session.selectList("com.sinc.mybatis.basket.selectMyBasketRows", map);
		
		// MYBASKET Table에서 나의 ID를 검색해서 SELECT 구문
		return session.selectList("com.sinc.mybatis.basket.selectMyBasketRows", obj);
	}

	@Override
	public List<Object> barcodeBasketRow(String user_Id) {
		System.out.println("barcodeBasketRow is running");
		Object obj2 = session.selectList("com.sinc.mybatis.basket.barcodeBasketRow", user_Id);
		System.out.println((ArrayList) obj2 + "@@@@@@@@@@@@@@@@");
		return session.selectList("com.sinc.mybatis.basket.barcodeBasketRow", user_Id);
	}

}
