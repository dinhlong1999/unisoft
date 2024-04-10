package com.example.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springmvc.model.Account;
import com.example.springmvc.model.Employee;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IEmployeeService;

@Controller
public class AccountController {

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IEmployeeService employeeService;
//	
//	@Autowired
//	private IRoleService roleService;
	@Autowired
	 private SimpMessagingTemplate messagingTemplate;
	
	private Account getAccountLogin () {	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String usernameLogin = authentication.getName();
			Account account  = accountService.getAccountByUsername(usernameLogin);
			return account;
		}
    	return null;
    }

	@GetMapping(value={"/login","/logoutSuccessful"})
	public String loginForm() {
		return "formlogin";
	}
	@GetMapping("/login/success")
	public String loginSuccess() {
		Account account = getAccountLogin();
		if (account.getRoleId() == 1) {
			return "redirect:/product/list";
		}
		Employee employee = employeeService.getEmployeeByAccountId(account.getId());
		int rowEffect = employeeService.updateStatusEmployee(1, employee.getVersion(), employee.getId());
		Employee employeeResult = employeeService.getEmployeeByAccountId(employee.getAccountId());
		messagingTemplate.convertAndSend("/topic/employeeStatus", employeeResult);
		return "redirect:/product/list";
	}

	
	@GetMapping("/403")
	public String accessDenied() {
		return "403Page";
	}
	
	
}
