package com.example.springmvc.common;

import com.example.springmvc.model.Account;



public class CheckLogin {
	
	public boolean isLogin(Account account) {
		if (account == null) {
			return false;
		}
		return true;
	}
	

}
