package com.sinc.project.product.dao;

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
	public Object BasketRow(Object obj) {
		System.out.println("BasketDaoImpl is running");
		System.out.println(obj);
		
		setBasketVo((BasketVO) obj);
		
		Object obj2 = session.insert("com.sinc.mybatis.basket.BasketRow", obj);
		return null;
	}

	@Override
	public List<Object> BasketRow2(Object obj) {
		System.out.println("BasketDaoImpl is running");
		return null;
	}

	public BasketVO getBasketVo() {
		return basketVo;
	}

	public void setBasketVo(BasketVO basketVo) {
		this.basketVo = basketVo;
	}

}
