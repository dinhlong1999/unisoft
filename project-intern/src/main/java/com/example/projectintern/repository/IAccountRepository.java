package com.example.projectintern.repository;

import com.example.projectintern.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  IAccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUsername(String username);
}
