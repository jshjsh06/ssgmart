package com.sinc.project.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinc.project.user.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao dao;
	
	@Override
	public Object login(Object obj) {
		System.out.println("Service login~~~~~~~~~~");
		return dao.loginRow(obj);
	}
	


}
