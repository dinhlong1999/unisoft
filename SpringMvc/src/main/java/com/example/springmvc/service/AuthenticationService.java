package com.example.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springmvc.model.Account;

@Service
public class AuthenticationService {

	@Autowired
	private IAccountService accountService;
	
	public Account getAccountLogin () {	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String usernameLogin = authentication.getName();
            return accountService.getAccountByUsername(usernameLogin);
		}
    	return null;
    }
}
