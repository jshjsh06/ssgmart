package com.sinc.project.basketdelete.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinc.project.basketdelete.service.basketDeleteService;
import com.sinc.project.model.vo.BasketVO;

@Controller
@RequestMapping(value = "/basketDelete")
public class basketDeleteCtrl {

	@Resource(name = "basketDeleteService")
	private basketDeleteService service;
	private BasketVO basketvo;
	
	public basketDeleteCtrl() {
		basketvo = new BasketVO();
	}
	
	@RequestMapping (value="/basketDelete.do", method = RequestMethod.GET)
	public String basketDelete() {
		System.out.println("basketDelete is running");
		Object result = service.basketDelete(this.basketvo);
		return "basketDelete/basketDelete"; // view 디렉터리에 basketDelete디렉터리 밑에 basketDelete.jsp파일을 실행시키는 라인
		
	} 
}
