package com.sinc.project.basket.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinc.project.basket.service.BasketService;
import com.sinc.project.model.vo.BasketVO;

@Controller
@RequestMapping(value = "/basket")
public class BasketCtrl {
	
	@Resource(name = "basketService")
	private BasketService service;
	private BasketVO basketvo;

	public BasketCtrl() {
		basketvo = new BasketVO();
	}

		
	@RequestMapping(value="/getBasket2.do", method = RequestMethod.GET)
	public String getBasket2() { 
		System.out.println("getBasket2 is running");
		Object result = service.getBasket(this.basketvo);
		
		return "basket/basket";
	}
}
