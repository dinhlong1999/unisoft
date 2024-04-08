package com.example.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IRoleService;

@Controller
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/login")
	public String loginForm() {
		return "formlogin";
	}
	@GetMapping("")
	public String loginSuccess() {
		return "test";
	}

	
	@GetMapping("/403")
	public String accessDenied() {
		return "403Page";
	}
}
