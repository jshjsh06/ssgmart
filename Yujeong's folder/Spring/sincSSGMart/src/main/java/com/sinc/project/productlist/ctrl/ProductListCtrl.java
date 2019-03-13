package com.sinc.project.productlist.ctrl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinc.project.model.vo.ProductListVO;
import com.sinc.project.productlist.service.ProductListService;

@Controller
@RequestMapping(value="/productList")
public class ProductListCtrl {
	
	@Resource(name = "productListService")
	private ProductListService service;
	
	private ProductListVO pListVO;
	
	public ProductListCtrl() {
		pListVO = new ProductListVO();
	}
	 
//	//전체 product 가져오기 where 조건 없음
//	@RequestMapping(value = "/productList.do", method = RequestMethod.GET)
//	public String getProductList(Model model) {
//		System.out.println("p_ListCtrl getProductList");
//		model.addAttribute("productlists", service.productList_list());
//		return "productList/productList";
//	}
	
	//===================where 매장(jsp용)
	@RequestMapping(value = "/productList3.do", method = RequestMethod.GET)
	public String getProductList3(ProductListVO pListVO, Model model) {
		String storeName = "명동센터";
		System.out.println("p_ListCtrl getProductList2");
		
		this.pListVO.setStoreName(storeName);
		
		model.addAttribute("productlists", service.productList_list1(this.pListVO));

		//model.addAttribute("productlists", toJson(service.productList_list2(this.pListVO)));
		
		return "productList/productList";
	}
	
	
	//====== where 매장(안드로이드용)	
	@RequestMapping(value = "/productList1.do")
	@ResponseBody
	public JSONArray getProductList2(@RequestParam("storeName") String storeName) {
		System.out.println("(매장)안드로이드로 들어가는 파람~~~");
		
		this.pListVO.setStoreName(storeName);
		
		return toJson(service.productList_list1(this.pListVO));
	}
	
	
	//====== where 매장+카테고리(jsp용)
	@RequestMapping(value = "/productList4.do", method = RequestMethod.GET)
	public String getProductList4(ProductListVO pListVO, Model model) {
		String storeName = "명동센터";
		String category = "베이커리";
		System.out.println("p_ListCtrl getProductList2");
		
		this.pListVO.setStoreName(storeName);
		this.pListVO.setCategory(category);
		//System.out.println("매장명 set 명동점"  + storeName);
		
		model.addAttribute("productlists", service.productList_list2(this.pListVO));

		
		return "productList/productList";
	}
	
	//====== where 매장+카테고리(안드로이드용)
	@RequestMapping(value = "/productList2.do", method = RequestMethod.GET)
	public JSONArray getProductList2(@RequestParam("storeName") String storeName, @RequestParam("category") String category) {
		System.out.println("(매장&카테고리)안드로이드로 들어가는 파람~~~");
		
		this.pListVO.setStoreName(storeName);
		this.pListVO.setCategory(category);
		
		return toJson(service.productList_list2(this.pListVO));
	}
	
	
	
	public JSONArray toJson(Object obj) { // Dao에서 최종 반환한 ArrayList 타입을 JSON 타입으로 바꾸어 리턴하는 함수
		
		System.out.println("toJson is running");

		JSONArray jArray = new JSONArray();
		ArrayList<ProductListVO> aryList = (ArrayList)obj;
		for(ProductListVO x : aryList) {
			JSONObject row = new JSONObject();	
			row.put("storeName", x.getStoreName());
			row.put("productName", x.getProductName());
			row.put("category", x.getCategory());
			row.put("price", x.getPrice());
			row.put("stock", x.getStock());
			row.put("valid", x.getValid());
			jArray.put(row);
		}
		System.out.println(jArray);
		return jArray;
	}
	


}
