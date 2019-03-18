package com.sinc.project.basket.dao;

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
		//System.out.println(obj);
		
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
	public List<Object> selectMyBasketRow(String username) { 
		System.out.println("selectMyBasketRow is running");		
		
<<<<<<< HEAD
		//Object obj2 = session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", user_Id);
		//System.out.println((ArrayList) obj2 + "@@@@@@@@@@@@@@@@");
=======
		Object obj2 = session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", user_Id);
//		System.out.println((ArrayList) obj2 + "@@@@@@@@@@@@@@@@");
>>>>>>> 0121a1eab31d1b40898c81fed75314f38dc5d816
		
		// MYBASKET Table에서 나의 ID를 검색해서 SELECT 구문
		return session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", username);
		//return session.selectList("com.sinc.mybatis.basket.selectMyBasketRow", obj);
	}
	
	@Override
	public void deleteMyBasketRow(Object obj) {
		System.out.println("deleteMyBasketRow is running");
//		System.out.println(obj);
		
<<<<<<< HEAD
		// MYBASKET Table에 Delete 하는 구문
		session.insert("com.sinc.mybatis.basket.deleteMyBasketRow", obj);
	}
	
	@Override
	public void updateBasketRow(List<Object> obj) { 
		System.out.println("updateBasketRow is running");
		System.out.println("obj : "+obj);
		System.out.println("size : " + obj.size());
		// discount를 결제 시점에 바꾸는 것.
		for(int i=0; i<obj.size(); i++ ) {
			System.out.println("obj.get : " + obj.get(i));
			session.insert("com.sinc.mybatis.basket.updateBasketRow", obj.get(i));
		}
=======
		// MYBASKET Table에 DELETE 하는 구문
		session.insert("com.sinc.mybatis.basket.deleteMyBasketRow", obj);
//		Object obj2 = session.selectList("com.sinc.mybatis.basket.selectMyBasketRows", map);
		
>>>>>>> 0121a1eab31d1b40898c81fed75314f38dc5d816
	}

	@Override
	public List<Object> barcodeBasketRow(String user_Id) {
		System.out.println("barcodeBasketRow is running");
		//Object obj2 = session.selectList("com.sinc.mybatis.basket.barcodeBasketRow", user_Id);
		//System.out.println((ArrayList) obj2 + "@@@@@@@@@@@@@@@@");
		return session.selectList("com.sinc.mybatis.basket.barcodeBasketRow", user_Id);
	}

}
