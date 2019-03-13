package com.sinc.project.cart.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("cartDao")
public class CartDaoImpl implements CartDao {

	@Resource(name = "sqlSession")			//root-context에 있는 이름으로
	private SqlSession session;
	
	
	@Override
	public List<Object> cartListsRow() {
		System.out.println("DaoImpl cartListsRow");
		return session.selectList("com.sinc.mybatis.cart.cartListsRow");
	}
	
	public void cartDelete(int cart_id) {
		System.out.println("DaoImpl cartDelete");
		session.delete("com.sinc.mybatis.cart.cartDelete", cart_id);
	}
	
}
