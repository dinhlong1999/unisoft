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
import com.example.springmvc.model.Role;
import com.example.springmvc.repository.AccountMapper;
import com.example.springmvc.repository.RoleMapper;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Check account exists in database ?
		Account accountCheckExists = accountMapper.getAccountByUsername(username);
		
		if (accountCheckExists == null) {
			throw new UsernameNotFoundException("User" + username + " khong ton tai trong he thong");
		}
		

		
		Role role = roleMapper.getRoleById(accountCheckExists.getRoleId());
		String roleOwn = role.getName();
		
		Set<GrantedAuthority> grantList = new HashSet<>();
		GrantedAuthority authority = new SimpleGrantedAuthority(roleOwn);
		grantList.add(authority);
		
		UserDetails userDetails = (UserDetails) new User(accountCheckExists.getUsername(),
				accountCheckExists.getPassword(), !accountCheckExists.isFlag(),true,true,true,grantList);
		return userDetails;
		
	}

}
