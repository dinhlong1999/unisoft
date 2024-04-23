package com.example.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springmvc.dto.AllocationDTO;
import com.example.springmvc.model.Account;
import com.example.springmvc.model.Allocation;
import com.example.springmvc.service.IAccountService;

@Controller
public class AllocationController {
	
	@Autowired
	private IAccountService accountService;
	
	private Account getAccountLogin () {
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   if (!(authentication instanceof AnonymousAuthenticationToken)) {
				String usernameLogin = authentication.getName();
				Account account  = accountService.getAccountByUsername(usernameLogin);
				return account;
			}
		    	return null;
		}

	@GetMapping("/allocation")
	public String allocationPurchase(Model model) {
		Account account = getAccountLogin();
		AllocationDTO allocationDTO = new AllocationDTO();
		model.addAttribute("nameLogin",account.getUsername());
		model.addAttribute("allocationDTO", allocationDTO);
		return "allocation/allocationProduct";
	}
	
	@PostMapping("/allocation")
	public String allocation(@ModelAttribute AllocationDTO allocationDTO ) {
		for(Allocation temp : allocationDTO.getAllocationList()) {
			System.out.println(temp);
		}
		return null;
	}
}
