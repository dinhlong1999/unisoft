package com.example.projectintern.service;
import com.example.projectintern.model.Account;
import com.example.projectintern.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;


    @Override
    public Account getAccountByUserName(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    @Transactional
    public void saveAccount(Account account) {
        accountRepository.saveAccount(account.getPassword(), account.getUsername(),account.getRole().getId());
    }

    @Override
    public int getAccountByUsername(String username) {
        return accountRepository.getAccountByUsername(username);
    }

    @Override
    public Map<String, Object> getAccountByUsernameAndPassword(String username, String password) {
        Map<String,Object> account = accountRepository.getAccountByUsernameAndPassword(username,password);
        return account ;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null){
            throw new UsernameNotFoundException("Sai tên đăng nhập");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
        UserDetails userDetails = new User(account.getUsername(),account.getPassword(),authorities);
        return userDetails;
    }
}
