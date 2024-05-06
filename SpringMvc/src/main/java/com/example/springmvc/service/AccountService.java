package com.example.springmvc.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.model.Account;
import com.example.springmvc.model.Role;
import com.example.springmvc.repository.AccountMapper;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Account getAccountByUsername(String username) {
		Map<String, Object> accountMap = accountMapper.getAccountByUsername(username);
		if (accountMap == null) {
			return null;
		}else {
			Account account = new Account();
			account.setId((int) accountMap.get("id"));
			account.setUsername((String) accountMap.get("username"));
			account.setPassword((String) accountMap.get("password"));
			Role role = new Role();
			role.setId((int) accountMap.get("roleId"));
			role.setName((String) accountMap.get("roleName"));
			account.setRole(role);
			return account;
		}
	
		
	}

	@Override
	public int checkUsernameOfAccount(String username) {
		return accountMapper.checkUsernameOfAccount(username);
	}

	@Override
	public int checkUsernameExists(String username, int id) {
		return accountMapper.checkUsernameExists(username, id);
	}
	
	
}
