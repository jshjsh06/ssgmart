package com.sinc.project.basketlist.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sinc.project.model.vo.BasketListVO;
import com.sinc.project.model.vo.ProductListVO;

@Repository("basketListDao")
public class BasketListDaoImpl implements BasketListDao {
	
	@Resource(name = "sqlSession")			//root-context에 있는 이름으로
	private SqlSession session;
	
	private BasketListVO bListVO;
	
	@Override
	public List<Object> basketList_listRow(Object obj){
		System.out.println("------ 장바구니 Dao");
		bListVO = (BasketListVO) obj;
		return session.selectList("com.sinc.mybatis.basketList.productStoreRow", obj);
	}
	

}
