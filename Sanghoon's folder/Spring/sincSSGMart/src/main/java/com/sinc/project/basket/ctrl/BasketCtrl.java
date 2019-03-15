package com.sinc.project.basket.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinc.project.basket.service.BasketService;
import com.sinc.project.model.vo.BasketVO;
import com.sinc.project.model.vo.MyBasketVO;
import com.sinc.project.model.vo.MyProductListVO;
<<<<<<< HEAD

=======
import com.sinc.project.model.vo.ProductListVO;
import com.sinc.project.model.vo.UserVO;
>>>>>>> 0121a1eab31d1b40898c81fed75314f38dc5d816

@Controller
@RequestMapping(value = "/basket")
public class BasketCtrl {
	
	@Resource(name = "basketService")
	private BasketService service;
	private BasketVO basketvo;
	private	Map<String, String> insertMap;
	private	Map<String, String> deleteMap;


	public BasketCtrl() {
		basketvo = new BasketVO();
		insertMap = new HashMap<String, String>();
		deleteMap = new HashMap<String, String>();
	}
	
	@RequestMapping(value = "/home.do") // 8080/product/product.do 가 실행되면 이 함수가 실행된다!!
	public String home() {
		System.out.println("home call~~~~~");
		return "pos/index"; // 이 리턴은 webapp에 있는 WEB-INF-views를 기준으로 한다!!!! product 폴더를 사용하고 싶으면 product/~~ 를 해야지!!
	}
	
	@RequestMapping(value="/insertMyBasket.do", method = RequestMethod.POST)
	@ResponseBody
	public void insertMyBasket(@RequestBody MyBasketVO myBasket) {  
		// 상품목록에서 내 장바구니로 Swifing시 내 장바구니에 목록이 담기는 Ctrl
		System.out.println("insertMyBasket is running");

		System.out.println(myBasket.getStoreName());
		service.insertMyBasket(myBasket);
		

	}
	
	@RequestMapping(value="/selectMyBasket.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> selectMyBasket(@RequestParam("user_Id") String id) {  
		System.out.println("selectMyBasket is running");
		
		System.out.println("user_Id : " + id);
		
		List<Object> list = service.selectMyBasket(id);
		
		for(int i=0; i< list.size(); i ++) {
			System.out.println(list.get(i));
		}
		
		return list;
	}
	
<<<<<<< HEAD
=======

>>>>>>> 0121a1eab31d1b40898c81fed75314f38dc5d816
	@RequestMapping(value="/deleteMyBasket.do", method = RequestMethod.POST)
	@ResponseBody
	public void deleteMyBasket(@RequestBody MyProductListVO myProductList) { 
		// 내장바구니에서 상품을 삭제하는 Ctrl. 거의 insertMyBasket와 유사
		System.out.println("deleteMyBasket is running");
<<<<<<< HEAD
		
		service.deleteMyBasket(myProductList);
	}
	
	
	@RequestMapping(value="/updateBasket.do", method = RequestMethod.POST)
	@ResponseBody
	public void updateBasket(@RequestBody List<Object> myProductList) {  
		
		System.out.println("updateBasket is running");	
		//System.out.println(myProductList);
		service.updateBasket(myProductList);

=======
		System.out.println(myProductList.toString());
		
//		List<Object> list = service.deleteMyBasket(myProductList);
		service.deleteMyBasket(myProductList);
		
>>>>>>> 0121a1eab31d1b40898c81fed75314f38dc5d816
	}
//	
//	@RequestMapping(value="/insertBasketId.do", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Object> insertBasketId(ProductListVO productList, UserVO user) { 
//		// 내장바구니에서 상품을 삭제하는 Ctrl. 거의 insertMyBasket와 유사
//		System.out.println("insertBasketId is running");
//		deleteMap.put("product_Id", productList.getProduct_Id());
//		deleteMap.put("user_Id", user.getUser_Id());
//		
//		List<Object> list = service.deleteMyBasket(deleteMap);
//		
//		return list;
//	}
	
	
	@RequestMapping(value="/pos.do", method = RequestMethod.POST) // ResonseBody를 사용하면 AJAX에서 자동으로 json으로 받을거에여!!
	@ResponseBody
	public List<Object> getBasketByBarcode(String user_Id) { 
		System.out.println("getBasketByBarcode is running");
		System.out.println(user_Id);
		
		List<Object> list = service.getBasketByBarcode(user_Id);
//		System.out.println(list);
		return list;
	}
}
