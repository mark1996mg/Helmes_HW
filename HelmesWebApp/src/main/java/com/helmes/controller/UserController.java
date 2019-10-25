package com.helmes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helmes.dao.UserDAO;
import com.helmes.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@PostMapping("/post")
	public User postUser(HttpServletRequest httpServletRequest, @RequestBody User user) {
		String sessionId = httpServletRequest.getSession().getId();
		if (user != null) {
			user.setUserId(sessionId);
			userDAO.insertOrUpdateUser(user);
		}
		return user;
	}
	
	@GetMapping("/getSession")
	public User getUserFromSession(HttpServletRequest httpServletRequest) {
		String sessionId = httpServletRequest.getSession().getId();
		return userDAO.getUser(sessionId);
	}
	
}
