package com.example.springmvc.service;


import com.example.springmvc.model.Account;

public interface IAccountService {

	Account getAccountByUsername(String username);
	
	int checkUsernameOfAccount(String username);
	
	int checkUsernameExists(String username, int id);
}
