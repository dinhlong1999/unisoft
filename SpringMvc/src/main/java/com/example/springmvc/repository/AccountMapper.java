package com.example.springmvc.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountMapper {
	Map<String, Object> getAccountByUsername(String username);
	
	int insertAccount(String username, String password, int roleId);
	
	int checkUsernameOfAccount(String username);
	
	int editAccount(String username, String password,int version,int id);
	
	int checkUsernameExists(String username, int id);
}
