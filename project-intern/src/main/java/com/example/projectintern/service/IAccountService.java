package com.example.projectintern.service;

import com.example.projectintern.model.Account;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface IAccountService extends UserDetailsService{
    Account getAccountByUserName(String username);
    void saveAccount(Account account);
    int getAccountByUsername( String username);

    Map<String,Object> getAccountByUsernameAndPassword(String username,String password);

    int updateAccount(String username, String password, int roleId, int accountId);
}
