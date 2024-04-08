package com.example.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.model.Account;
import com.example.springmvc.repository.AccountMapper;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return accountMapper.getAccountByUsername(username);
	}
	
	
}
