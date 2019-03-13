package com.sinc.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sinc.project.model.vo.ProductVO;
@Controller
public class TestController {
// return 타입이 String이면 return 에 명시된 이름(여기선 ok => ok.jsp)로 연결하여 보여

@RequestMapping(value = "/doA.do")
public String doA() {
	System.out.println("doA call");
	return "ok";
}
// return 타입이 void이면 논리적 페이지로 넘길 return이 없으므로 RequestMapping의

@RequestMapping(value = "/ok.do")
public void doB() {
	System.out.println("doB call");
}
// ModelAndView 리턴타입은 Model과 View를 동시에 설정이 가능하다!

@RequestMapping(value = "/model.do")
public ModelAndView doC() {
	System.out.println("doC call");
	ModelAndView mv = new ModelAndView();
	mv.setViewName("ok"); // 페이지 이름을 심음. ok.jsp로 이동하게 된다.
	//mv.addObject(attributeName, attributeValue);
	mv.addObject("msg", "hi~ 불렀어?"); //msg라는 변수에 hi~불렀어 라는 변수로 보내게

	return mv;
}
// 가장 많이 쓰이는 방식. ok.jsp로 보내게 되고, Model model을 인수로 받아서 model이 가

@RequestMapping(value = "/model2.do")
public String doD(Model model) {
	System.out.println("doD call");
	model.addAttribute("msg", "doD");
	return "ok";
}

/////////////////////////////////////////////////////////////////////
//인자로 ProductVO처럼 객체를 넣어주면 자동으로 setter를 찾아준다!
@RequestMapping(value = "/param.do")
public String doParam(ProductVO product, RedirectAttributes attr) {
	System.out.println("param obj : " + product);
	attr.addFlashAttribute("msg", product); // 이 정보를 redirect.do -> ok.jsp까
	
	return "redirect:/redirect.do";
}
@RequestMapping(value = "/redirect.do")
public String doSend(@ModelAttribute("msg") ProductVO msg) {
	System.out.println("doSend call~");
return "ok";
}
}