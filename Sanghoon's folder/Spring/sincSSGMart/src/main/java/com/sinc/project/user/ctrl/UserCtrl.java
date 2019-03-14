package com.sinc.project.user.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinc.project.model.vo.UserVO;
import com.sinc.project.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserCtrl {


	@Resource(name = "userService") 	//의존관계주입
	private UserService service;	//interface type으로
	
	 @RequestMapping(value = "/android.do")
	 public @ResponseBody UserVO androidLogin(@RequestParam("username") String id, @RequestParam("password") String pwd) {
	  System.out.println("Android login");
	  UserVO userVO = new UserVO();
	  userVO.setUser_Id(id);
	  userVO.setUser_Pwd(pwd);
	  
	  System.out.println(userVO.toString());
	  
	  Object result = service.login(userVO);
	  
	  if(result == null) {
	   System.out.println("로그인실패");
	   return (UserVO)result;
	  }
	  else {
		  System.out.println("로그인성공");
		  return (UserVO)result;
	  }
		/*
		 * System.out.println("로그인성공"); return (UserVO)result;
		 */
	 }
	
}
