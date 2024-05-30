package com.example.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springmvc.service.AuthenticationService;



@Controller
public class AccountController {

	
//	@Autowired
//	private IEmployeeService employeeService;
//
//	@Autowired
//	 private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private AuthenticationService authenticationService;
	

	@GetMapping("/login")
	public String loginForm(HttpServletRequest request) {
		if (authenticationService.getAccountLogin() != null) {
			return "redirect:/product/list";
		}
		return "account/formlogin";
	}
	
	
	
//	@GetMapping("/login/success")
//	public String loginSuccess(HttpServletRequest request) {
//		
//		return "redirect:/product/list";
//		
////		Account account = getAccountLogin();
////		if (account.getRole().getId() == 1) {
////			return "redirect:/product/list";
////		}
////		Employee employee = employeeService.getEmployeeByAccountId(account.getId());
////		int rowEffect = employeeService.updateStatusEmployee(1, employee.getVersion(), employee.getId());
////		Employee employeeResult = employeeService.getEmployeeByAccountId(employee.getAccount().getId());
////		messagingTemplate.convertAndSend("/topic/employeeStatus", employeeResult);
////		return "redirect:/product/list";
//	}
	
	
	@GetMapping("/logoutSuccessful")
	public String logOut(HttpServletRequest request) {
		return "account/formlogin";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "account/403Page";
	}

	
	
}
