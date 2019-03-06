package com.sinc.project.product.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinc.project.product.service.ProductService;
import com.sinc.project.store.model.vo.StoreVO;

import org.json.simple.JSONObject;

// ProductCtrl는 매장에서 판매하고 있는 상품목록을 리턴을 중점으로 한다.
@Controller
@RequestMapping(value="/product") // 맵핑을 http://localhost:8088/product에 맵핑한다.이 클래스는 이 경로에 붙게된다.
public class ProductCtrl {
	@Resource(name="productService") // productServiceImpl에서 productService로 명명하였으므로 여기서 이어준다.
	private ProductService service;
	
	private StoreVO storevo;
	
	public ProductCtrl() {
		storevo = new StoreVO();
	}
	
	@RequestMapping(value = "/product.do") // 8080/product/product.do 가 실행되면 이 함수가 실행된다!!
	public String home() {
		System.out.println("home call~~~~~");
		return "product/product"; // 이 리턴은 webapp에 있는 WEB-INF-views를 기준으로 한다!!!! product 폴더를 사용하고 싶으면 product/~~ 를 해야지!!
	}
	//public void getProducts(@RequestBody StoreVO storevo)

	@RequestMapping(value="/getProducts.do", method = RequestMethod.POST)
	public String getProducts(StoreVO storevo) { // @RequestBody를 통해 외부에서 들어오는 JSON 형식을 자바 형식으로 바꿔준다.
		// @RequestBody를 통해서만 StoreVO로 값이 들어간다고 생각했는데..
		// 그냥 JSON 형식을 받아도 자동으로 JSON에 해당되는 키값을 매칭해서 바로 StoreVO에 매칭해주는 것 같다.
		// product.jsp에서 ajax로 json형식인 data: {"id":"2", "name":"2"}으로 보냈는데 @RequestBody 없어도 자동으로 매칭해서 들어감
		// 오히려 @RequestBody를 쓰면은 오류가 생김. 이 이유를 좀 생각해봐야함.. 뭔가.. 이해가 어렵다 ㅠ ★★★★★

		this.storevo.setId(storevo.getId());
		this.storevo.setName(storevo.getName());
		System.out.println("getProducts is running");
		Object result = service.getProducts(this.storevo);
		
		return "product/product";
	}

	/**
	@RequestMapping(value="/getProducts.do", method = RequestMethod.POST)
	public String getProducts(JSONObject obj) { // @RequestBody를 통해 외부에서 들어오는 JSON 형식을 자바 형식으로 바꿔준다.
		JSONObject jobj = new JSONObject(obj);
		System.out.println(jobj);
		System.out.println("getProducts is running");
		Object result = service.getProducts(this.storevo);
		
		return "product/product";
	}
		**/
}
