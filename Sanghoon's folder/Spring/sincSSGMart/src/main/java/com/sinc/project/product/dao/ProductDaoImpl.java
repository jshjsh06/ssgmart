package com.sinc.project.product.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sinc.project.store.model.vo.StoreVO;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{

	@Resource(name="sqlSession")
	private SqlSession session;
	
	private StoreVO storeVo;
	
	@Override
	public Object productsRow(Object obj) {
		System.out.println("ProductDaoImpl is running");
		System.out.println(obj);
		
		storeVo = (StoreVO) obj;
		System.out.println(storeVo.getId() + "///" + storeVo.getName());
		
		Object obj2 = session.selectOne("com.sinc.mybatis.store.productsRow", obj);
		System.out.println(((StoreVO)obj2).getAddress());
		
		return null;
	}

}
