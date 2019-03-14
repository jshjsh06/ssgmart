package com.sinc.project.basketlist.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinc.project.basketlist.service.BasketListService;
import com.sinc.project.model.vo.BasketListVO;

@Controller
@RequestMapping(value="/basketList")
public class BasketListCtrl {
	
	@Resource(name = "basketListService")
	private BasketListService service;
	
	private BasketListVO bListVO;
	
	public BasketListCtrl() {
		bListVO = new BasketListVO();
	}

	@RequestMapping(value = "/basketList.do", method = RequestMethod.GET)
	public String getProductList4(BasketListVO bListVO, Model model) {
		String order_Id = "111";
		System.out.println("=======jsp, 장바구니 Controller");		
		this.bListVO.setStoreName(order_Id);
		System.out.println(order_Id);
		model.addAttribute("productlists", service.basketList_list(this.bListVO));		
		return "productList/productList";
	}
}
