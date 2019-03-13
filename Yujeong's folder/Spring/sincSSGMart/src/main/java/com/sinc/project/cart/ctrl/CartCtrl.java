package com.sinc.project.cart.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sinc.project.cart.service.CartService;

@Controller
@RequestMapping(value = "/cart")
public class CartCtrl {
	
	@Resource(name="cartService")
	private CartService service;
	
	
	@RequestMapping(value = "/cart.do", method = RequestMethod.GET)
	public String getCartLists(Model model) {
		System.out.println("CartCtrl getCartLists");
		model.addAttribute("cartlists", service.cartCartLists());
		return "cart/cartList";
	}
	
	//장바구니 삭제
	@RequestMapping(value = "/delete.do")
	public String cartDelete(@RequestParam int cart_id) {
		System.out.println("CartCtrl cartDelete");
		service.cartDelete(cart_id);
		return "redirect:/cart/cartList";
		
	}
	


}
