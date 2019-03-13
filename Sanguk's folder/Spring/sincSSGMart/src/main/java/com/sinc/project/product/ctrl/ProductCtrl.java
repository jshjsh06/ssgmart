package com.sinc.project.product.ctrl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinc.project.model.vo.StockStoreVO;
import com.sinc.project.model.vo.StoreVO;
import com.sinc.project.product.service.ProductService;

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

	@RequestMapping(value="/getProducts2.do", method = RequestMethod.POST)
	public String getProducts2(StoreVO storevo) { // @RequestBody를 통해 외부에서 들어오는 JSON 형식을 자바 형식으로 바꿔준다.
		// @RequestBody를 통해서만 StoreVO로 값이 들어간다고 생각했는데..
		// 그냥 JSON 형식을 받아도 자동으로 JSON에 해당되는 키값을 매칭해서 바로 StoreVO에 매칭해주는 것 같다.
		// product.jsp에서 ajax로 json형식인 data: {"id":"2", "name":"2"}으로 보냈는데 @RequestBody 없어도 자동으로 매칭해서 들어감
		// 오히려 @RequestBody를 쓰면은 오류가 생김. 이 이유를 좀 생각해봐야함.. 뭔가.. 이해가 어렵다 ㅠ ★★★★★

		this.storevo.setId(storevo.getId());
		this.storevo.setName(storevo.getName());
		
		System.out.println("getProducts2 is running");
		Object result = service.getProducts(this.storevo);
		
		return "product/productList";
	}
	
	@RequestMapping(value="/getProducts3.do", method = RequestMethod.POST)
	public @ResponseBody String getProducts3(StoreVO storevo, Model model) { 
		// StoreVO는 product.jsp에서 submit으로 입력받은 값을 저장하기 위해
		// Model은 "lists"라는 이름으로 productList에 값을 전달하기 위해 사용하게 된다!!
		System.out.println("storevo : " + storevo.toString());
		this.storevo.setId(storevo.getId());
		this.storevo.setName(storevo.getName());
		System.out.println("this.storevo : " + storevo.toString());
		System.out.println("getProducts is running");
		model.addAttribute("lists", service.getProducts2(this.storevo));
//		System.out.println(model.addAttribute("lists", service.getProducts2(this.storevo)));
//		Object result = service.getProducts(this.storevo);
		
		return "product/productList";
	}

	@RequestMapping(value="/getProducts.do", method = RequestMethod.POST)
	public String getProducts(@ModelAttribute StoreVO storevo, Model model) { 
		// @ModelAttribute는 @getParams와 달리 객체 자체를 받을 수 있고, 또한 자동으로 VO와 연결해서 풀어진다. 단일 변수를 받고 싶으면 getParams를 쓰면된다.
		// 사실 @ModelAttribute를 굳이 쓰지 않아도 되지만 써봄.
		System.out.println("storevo : " + storevo.toString());
		this.storevo.setId(storevo.getId());
		this.storevo.setName(storevo.getName());
		System.out.println("this.storevo : " + storevo.toString());
		System.out.println("getProducts is running");
//		model.addAttribute("lists", service.getProducts2(this.storevo));
		model.addAttribute("lists", toJson(service.getProducts2(this.storevo)));
		
		return "product/productList";
	}
	
	public JSONArray toJson(Object obj) { // Dao에서 최종 반환한 ArrayList 타입을 JSON 타입으로 바꾸어 리턴하는 함수
		System.out.println("toJson is running");
		JSONObject jsonMain = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		ArrayList<StockStoreVO> aryList = (ArrayList)obj;
		
		JSONObject row = new JSONObject();
		
		for(StockStoreVO x : aryList) {
			row.put("id", x.getId());
			row.put("address", x.getAddress());
			row.put("productName", x.getProductName());
			row.put("units", x.getUnits());
			jArray.add(row); 
			// 좋긴한데 이해가안되는게, JSONObject row가 JSONArray 에 담기고나면 안에 내용이 초기화 된다는 것이다.
			// 꼭 복사 붙이기가 아닌, 잘라내기 붙이기한 것 처럼..
//			System.out.println("row : " + row);
//			System.out.println("ary : " + jArray);
		}
		System.out.println("row : " + row);
		System.out.println("ary2 : " + jArray);
//		jsonMain.put("프로토콜", jArray); // 프로토콜을 담아서 넘길거라면 이렇게

		return jArray;
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
