package com.example.springmvc.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.springmvc.model.Employee;
import com.example.springmvc.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.model.Account;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IOrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IAccountService accountService;

	@Autowired
	private IEmployeeService employeeService;
	    
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
    public String showListOrder(@RequestParam(required = false,defaultValue = "0") int page,
    							@RequestParam(required = false,defaultValue = "") String username,
    							@RequestParam(required = false,defaultValue = "") String employeeName,
    							@RequestParam(required = false,defaultValue = "") String codeProduct,
    							@RequestParam(required = false,defaultValue = "") String nameProduct,
    							@RequestParam(required = false,defaultValue = "") String customerName,
    							@RequestParam(required = false,defaultValue = "") String phoneNumber,
    							@RequestParam(required = false,defaultValue = "") String dateStart, 
    							@RequestParam(required = false,defaultValue = "") String dateEnd,
    							@RequestParam(required = false,defaultValue = "0") Integer statusAllocation,
    							@RequestParam(required = false,defaultValue = "0") Integer statusBooking,Model model){
    	if (page != 0) {
    		page = page - 1;
		}
    	int limit = 4;
    	if (dateStart.isEmpty()) {
    		dateStart = "2000-10-10";
    	}else {
    		model.addAttribute("dateStart", dateStart);
    	};
    	if (dateEnd.isEmpty()) {
			dateEnd = "9999-10-10";
		}else {
			model.addAttribute("dateEnd", dateEnd);
		}
    	 if (statusAllocation == 0 && statusBooking == 0 ) {
            statusAllocation = 2;
            statusBooking = 1;
         };

    	
		 Account accountLogin = getAccountLogin();
		 String roleName = accountLogin.getRole().getName();
		 Employee employee = employeeService.getEmployeeByAccountId(accountLogin.getId());
		 int employeeId = employee.getId();

    	List<Map<String, Object>> ordersMap = orderService.getListOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber,(roleName.equals("ROLE_ADMIN")),employeeId, LocalDate.parse(dateStart), LocalDate.parse(dateEnd), statusAllocation,statusBooking, limit, limit * page);
    	int totalRecordByListAccount = orderService.getTotalRecordByOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber,(roleName.equals("ROLE_ADMIN")),employeeId, LocalDate.parse(dateStart), LocalDate.parse(dateEnd), statusAllocation,statusBooking);
    	double temp = (double) totalRecordByListAccount / limit;
    	int totalPage = (int) Math.ceil(temp);
    	
    	//logic phân trang
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
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("showStartEllipsis",showStartEllipsis);
        model.addAttribute("showEndEllipsis",showEndEllipsis);
        
        model.addAttribute("ordersMap", ordersMap);
        model.addAttribute("nameLogin", accountLogin.getUsername());
        model.addAttribute("roleName",roleName);
        
        model.addAttribute("username", username);
        model.addAttribute("employeeName", employeeName);
        model.addAttribute("codeProduct", codeProduct);
        model.addAttribute("nameProduct", nameProduct);
        model.addAttribute("customerName", customerName);
        model.addAttribute("phoneNumber", phoneNumber);
        
      
        model.addAttribute("statusAllocation", statusAllocation);
        model.addAttribute("statusBooking", statusBooking);
        return "order/listOrder";
    }
    
    @GetMapping("/save")
    public String saveOrders(@RequestParam List<Object> data,Model model ) {
    	
    	
    	return "orders/list";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
