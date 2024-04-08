package com.example.springmvc.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmvc.model.Account;

@Mapper
public interface AccountMapper {
	Account getAccountByUsername(String username);
}
