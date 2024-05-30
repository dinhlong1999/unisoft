package com.example.springmvc.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springmvc.model.Account;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private IAccountService accountService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Check account exists in database ?
		Account accountCheckExists = accountService.getAccountByUsername(username);
		
		if (accountCheckExists == null) {
			throw new UsernameNotFoundException("User" + username + " khong ton tai trong he thong");
		}
		
		System.out.println(accountCheckExists.toString());
		Set<GrantedAuthority> grantList = new HashSet<>();
		GrantedAuthority authority = new SimpleGrantedAuthority(accountCheckExists.getRole().getName());
		grantList.add(authority);
		
		UserDetails userDetails = (UserDetails) new User(accountCheckExists.getUsername(),
				accountCheckExists.getPassword(), !accountCheckExists.isFlag(),true,true,true,grantList);
		return userDetails;
		
	}

}
