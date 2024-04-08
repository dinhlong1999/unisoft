package com.example.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.model.Role;
import com.example.springmvc.repository.RoleMapper;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleById(id);
	}

}
