package com.sinc.project.basketdelete.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sinc.project.model.vo.BasketVO;

@Repository("basketDeleteDao")
public class basketDeleteDaoImpl implements basketDeleteDao{

	@Resource(name = "sqlSession")
	private SqlSession session;
	private BasketVO basketVo;
	
	@Override
	public Object basketDeleteRow(Object obj) {
		System.out.println("basketDeleteDaoImpl is running");
		System.out.println(obj);
		
		setBasketVo ((BasketVO) obj);
		
		Object obj2 = session.insert("com.sinc.mybatis.basket.BasketDeleteRow", obj);
		
		return null;
	}

	@Override
	public List<Object> basketDeleteRow2(Object obj) {
		System.out.println("basketDeleteDaoImpl is running");
		return null;
	}

	public BasketVO getBasketVo() {
		return basketVo;
	}

	public void setBasketVo(BasketVO basketVo) {
		this.basketVo = basketVo;
	}
}
