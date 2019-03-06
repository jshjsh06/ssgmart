package com.sinc.project.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sinc.project.model.vo.StockStoreVO;
import com.sinc.project.model.vo.StoreVO;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{

	@Resource(name="sqlSession")
	private SqlSession session;
	
	private StoreVO storeVo;
	private StockStoreVO stockStoreVo;
	
	@Override
	public Object productsRow(Object obj) {
		System.out.println("ProductDaoImpl is running");
		System.out.println(obj);
		
		storeVo = (StoreVO) obj;
		System.out.println(storeVo.getId() + "///" + storeVo.getName());
		
		Object obj2 = session.selectList("com.sinc.mybatis.stockstore.stockStoreRow", obj);
		
		System.out.println((ArrayList)obj2);
		
		return session.selectList("com.sinc.mybatis.stockstore.stockStoreRow", obj);
	}

	@Override
	public List<Object> productsRow2(Object obj) {
		System.out.println("ProductDaoImpl2 is running");
		Object obj2 = session.selectList("com.sinc.mybatis.stockstore.stockStoreRow", obj);
		System.out.println((ArrayList)obj2);
		
		return session.selectList("com.sinc.mybatis.stockstore.stockStoreRow", obj);
	}

}
