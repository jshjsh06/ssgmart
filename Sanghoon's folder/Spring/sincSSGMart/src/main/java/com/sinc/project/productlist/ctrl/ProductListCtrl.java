package com.sinc.project.productlist.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
	 
	// where 매장(안드로이드용)	
	@RequestMapping(value = "/productList1.do")
	@ResponseBody
	public List<Object> getProductList2(@RequestParam("storeName") String storeName) {	
		System.out.println("======= Android, 매장 controller");
		System.out.println("param : "+ storeName); 
		this.pListVO.setStoreName(storeName);
		
			
		List<Object> list  = service.productList_list1(this.pListVO);
		return list ; 
	}
	
	// where 매장+카테고리(안드로이드용)
	@RequestMapping(value = "/productList2.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getProductList2(@RequestParam("storeName") String storeName, @RequestParam("category") String category) {
		System.out.println("======= Android, 매장&카테고리 controller");
		
		this.pListVO.setStoreName(storeName);
		this.pListVO.setCategory(category);
		List<Object> list  = service.productList_list2(this.pListVO);
		System.out.println("list : " + list);
		return list ; 
	}
	
	
	//************************************************************************************************************
	// 전체 product 가져오기 where 조건 없음
	@RequestMapping(value = "/productList.do", method = RequestMethod.GET)
	public String getProductList(Model model) {
		System.out.println("p_ListCtrl getProductList");
		model.addAttribute("productlists", service.productList_list());
		return "productList/productList";
	}
	
	//  where 매장(jsp용)
	@RequestMapping(value = "/productList3.do", method = RequestMethod.GET)
	public String getProductList3(ProductListVO pListVO, Model model) {
		String storeName = "명동센터점";
		System.out.println("=======jsp, 매장 Controller");
		this.pListVO.setStoreName(storeName);		
		model.addAttribute("productlists", service.productList_list1(this.pListVO));
	
		return "productList/productList";
	}
	
	// where 매장+카테고리(jsp용)
	@RequestMapping(value = "/productList4.do", method = RequestMethod.GET)
	public String getProductList4(ProductListVO pListVO, Model model) {
		String storeName = "명동센터";
		String category = "베이커리";
		System.out.println("=======jsp, 매장&카테고리 Controller");		
		this.pListVO.setStoreName(storeName);
		this.pListVO.setCategory(category);		
		model.addAttribute("productlists", service.productList_list2(this.pListVO));		
		return "productList/productList";
	}

}
