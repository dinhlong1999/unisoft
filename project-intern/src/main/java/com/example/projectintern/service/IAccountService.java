package com.example.projectintern.service;

import com.example.projectintern.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService{
    Account getAccountByUserName(String username);
    void saveAccount(Account account);
}
