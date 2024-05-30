package com.example.springmvc.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmvc.model.Role;

@Mapper
public interface RoleMapper {
	
	Role getRoleById(int id);
}
