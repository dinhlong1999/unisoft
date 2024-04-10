package com.example.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springmvc.model.Account;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IAccountService accountService;
	
	private Account getAccountLogin () {
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   if (!(authentication instanceof AnonymousAuthenticationToken)) {
		  String usernameLogin = authentication.getName();
		  Account account  = accountService.getAccountByUsername(usernameLogin);
		  return account;
	   }
	      return null;
	   }
	
	@GetMapping("/list")
	public String getAllEmployee(@RequestParam(required = false, defaultValue = "0") int page,
								 @RequestParam(required = false, defaultValue = "") String username,
								 @RequestParam(required = false, defaultValue = "") String employeeName,
								 @RequestParam(required = false, defaultValue = "") String phoneNumber,
								 Model model) {
		if (page != 0) {
			page = page - 1 ;
		}
		int limit = 3 ;
		Account account = getAccountLogin();
		List<Map<String, Object>> listEmployee = employeeService.getAllEmployee(username, employeeName,phoneNumber, limit, limit * page);
		int totalRow = employeeService.countTotalRow(username, employeeName, phoneNumber);
		double temp = (double) totalRow / limit ;
		
		
		
		int totalPage = (int) Math.ceil(temp);
		int maxVisitablePages = 10; //Số trang tối đa hiển thị
        int adjacentPages = 2;  //số trang bên cạnh trang hiện tại
        int startPage;
        int endPage;
        boolean showStartEllipsis = false; // Dấu ... đầu
        boolean showEndEllipsis = false;  // Dấu ... cuối
        if (totalPage <= maxVisitablePages) {
       	    startPage = 1;
       	    endPage = totalPage;
       	} else {
       	    if (page <= maxVisitablePages - adjacentPages) {
       	        startPage = 1;
       	        endPage = maxVisitablePages;
       	        showEndEllipsis = true;
       	    } else if (page >= totalPage - adjacentPages) {
       	        startPage = totalPage - maxVisitablePages + 1;
       	        endPage = totalPage;
       	        showStartEllipsis = true;
       	    } else {
       	        startPage = page - adjacentPages;
       	        endPage = page + adjacentPages;
       	        showStartEllipsis = true;
       	        showEndEllipsis = true;
       	    }
       	}
        model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("limit",limit);
		model.addAttribute("startPage",startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("showStartEllipsis",showStartEllipsis);
        model.addAttribute("showEndEllipsis",showEndEllipsis);
		
		model.addAttribute("listEmployee",listEmployee);
		model.addAttribute("username", username);
		model.addAttribute("employeeName", employeeName);
		model.addAttribute("phoneNumber", phoneNumber);
		
		model.addAttribute("nameLogin",account.getUsername());
		return "listEmployee";
	}
	
	@PostMapping("/delete") 
	public String deleteEmployee(@RequestParam int idDelete , RedirectAttributes redirectAttributes) {
		int rowEffect = 0;
		try {
			rowEffect = employeeService.deleteEmployeeById(idDelete);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		if (rowEffect == 1) {
			redirectAttributes.addFlashAttribute("message", "Xóa thành công");
			return "redirect:/employee/list";
		}else {
			redirectAttributes.addFlashAttribute("message", "Xóa thất bại ");
			return "redirect:/employee/list";
		}
	}
}
