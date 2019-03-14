package com.sinc.project.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Resource(name = "sqlSession")			//root-context에 있는 이름으로
	private SqlSession session;
	
	@Override
	public Object loginRow(Object obj) {	
		System.out.println("Dao loginRow");
		return session.selectOne("com.sinc.mybatis.user.loginRow", obj);
	}
	

}
