package com.sinc.project.index.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCtrl {
	
	@RequestMapping(value = "/index.do")
	public String home() {
		System.out.println("home call");
		return "home";
	}
}
