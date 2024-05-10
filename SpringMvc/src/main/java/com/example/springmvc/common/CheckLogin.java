package com.example.springmvc.common;

public class CheckLogin {
	public static String checkLogin(String name) {
		if(name == null) {
			return "redirect:/login/success";
		}
		return null;
	}
}
