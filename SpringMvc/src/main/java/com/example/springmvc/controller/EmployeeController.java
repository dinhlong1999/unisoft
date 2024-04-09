package com.example.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("/list")
	public String getAllEmployee(@RequestParam(required = false, defaultValue = "0") int page,
								 @RequestParam(required = false, defaultValue = "") String username,
								 @RequestParam(required = false, defaultValue = "") String employeeName,
								 @RequestParam(required = false, defaultValue = "") String phoneNumber,
								 Model model) {
		if (page != 0) {
			page = page - 1 ;
		}
		int limit = 4 ;
		
		List<Map<String, Object>> listEmployee = employeeService.getAllEmployee(username, employeeName,phoneNumber, limit, limit * page);
		int totalRow = employeeService.countTotalRow(username, employeeName, phoneNumber);
		double temp = (double) totalRow / limit ;
		int totalPage = (int) Math.ceil(temp);
		model.addAttribute("listEmployee",listEmployee);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("username", username);
		model.addAttribute("employeeName", employeeName);
		model.addAttribute("phoneNumber", phoneNumber);
		model.addAttribute("limit",limit);
		return "listEmployee";
	}
	
}
