package com.sinc.project.productlist.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sinc.project.model.vo.ProductListVO;
import com.sinc.project.model.vo.StoreVO;

@Repository("productListDao")
public class ProductListDaoImpl implements ProductListDao {

	@Resource(name = "sqlSession")			//root-context에 있는 이름으로
	private SqlSession session;
	
	private ProductListVO pListVO;
	
	
	@Override
	public List<Object> productList_listRow(){
		System.out.println("DaoImpl pList_ListRow");
		return session.selectList("com.sinc.mybatis.productList.productRow");
	}
	
	@Override
	public List<Object> productList_listRow1(Object obj){
		System.out.println("매장 불러오는 Dao");
		pListVO = (ProductListVO) obj;
		
		//System.out.println(pListVO.getStoreName()+ " /// " + pListVO.getCategory() + " /// " + pListVO.getPrice()+"/// " +pListVO.getProductName());
		
		//Object obj2 = session.selectList("com.sinc.mybatis.productList.productStoreRow", obj);
		//System.out.println("1");
		//System.out.println((ArrayList)obj2);
		//System.out.println("2");
		
		return session.selectList("com.sinc.mybatis.productList.productStoreRow", obj);
	}
	
	@Override
	public List<Object> productList_listRow2(Object obj){
		
		System.out.println("매장 + 카테고리 Dao");
		pListVO = (ProductListVO) obj;
		System.out.println(pListVO.getStoreName()+ " /// " + pListVO.getCategory() + " /// " + pListVO.getPrice()+"/// " +pListVO.getProductName());	
		Object obj2 = session.selectList("com.sinc.mybatis.productList.productCategoryRow", obj);
		System.out.println("1");
		System.out.println((ArrayList)obj2);
		System.out.println("2");
		return session.selectList("com.sinc.mybatis.productList.productCategoryRow", obj);
	}
	
	
}
