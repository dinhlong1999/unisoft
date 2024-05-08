package com.example.springmvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.example.springmvc.common.Paging;
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
    public String show(@RequestParam(required = false,defaultValue = "0") int page,
    							@RequestParam(required = false,defaultValue = "") String accountName,
    							@RequestParam(required = false,defaultValue = "") String employeeName,
    							@RequestParam(required = false,defaultValue = "") String productCode,
    							@RequestParam(required = false,defaultValue = "") String productName,
    							@RequestParam(required = false,defaultValue = "") String customerName,
    							@RequestParam(required = false,defaultValue = "") String customerPhone,
    							@RequestParam(required = false,defaultValue = "") String orderDayBegin, 
    							@RequestParam(required = false,defaultValue = "") String orderDayEnd,
    							@RequestParam(required = false,defaultValue = "0") Integer statusAllocation,
    							@RequestParam(required = false,defaultValue = "0") Integer statusBooking,Model model){
    	Account accountLogin = getAccountLogin();
    	String roleName = accountLogin.getRole().getName();
    	try {
    		 if (page != 0) {
    			 page = page - 1;
    		 }
    		 int limit = 4;
    		 if (orderDayBegin.isEmpty()) {
    			 orderDayBegin = "2000-10-10";
    		 }else {
    			 model.addAttribute("orderDayBegin", orderDayBegin);
    		 };
    		 if (orderDayEnd.isEmpty()) {
    			 orderDayEnd = "9999-10-10";
    		 }else {
    			 model.addAttribute("orderDayEnd", orderDayEnd);
    		 }
    		 if (statusAllocation == 0 && statusBooking == 0 ) {
    			 statusAllocation = 2;
    			 statusBooking = 1;
    		 };
         
    		 if (orderDayBegin.length() > 10) {
    			 orderDayBegin = "9999-10-10";
    		 }else if (orderDayBegin.length() < 10) {
    			 orderDayBegin = "1000-10-10";
    		 }
    		 if (orderDayEnd.length() > 10) {
    			 orderDayEnd = "9999-10-10";
    		 }else if (orderDayEnd.length() < 10) {
    			 orderDayEnd = "1000-10-10";
    		 }
        
    		
    		 Employee employee = employeeService.getEmployeeByAccountId(accountLogin.getId());
    		 int employeeId = employee.getId();
    		 List<Map<String, Object>> ordersMap = orderService.getListOrder(accountName, employeeName, productCode, productName, customerName, customerPhone,
    											 (roleName.equals("ROLE_ADMIN")),employeeId, LocalDate.parse(orderDayBegin), 
    											 LocalDate.parse(orderDayEnd), statusAllocation,statusBooking, limit, limit * page);
    		 int totalRecordByListAccount = orderService.getTotalRecordByOrder(accountName, employeeName, productCode, productName, customerName, customerPhone,
        																 (roleName.equals("ROLE_ADMIN")),employeeId, LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), 
        																  statusAllocation,statusBooking);
		
        	 double temp = (double) totalRecordByListAccount / limit;
        	 int totalPage = (int) Math.ceil(temp);
    	
        	 //Send variable to handle paging
        	 Map<String,Object> handlePaging = Paging.handlePaging(page, totalPage);
        	 int startPage = (int) handlePaging.get("startPage");
        	 int endPage = (int) handlePaging.get("endPage");
        	 boolean showStartEllipsis = (boolean) handlePaging.get("showStartEllipsis");
        	 boolean showEndEllipsis = (boolean) handlePaging.get("showEndEllipsis");
    	
        	 model.addAttribute("startPage",startPage);
        	 model.addAttribute("endPage", endPage);
        	 model.addAttribute("page",page);
        	 model.addAttribute("limit",limit);
        	 model.addAttribute("totalPage",totalPage);
        	 model.addAttribute("showStartEllipsis",showStartEllipsis);
        	 model.addAttribute("showEndEllipsis",showEndEllipsis);
        
        	 model.addAttribute("ordersMap", ordersMap);
        	 model.addAttribute("nameLogin", accountLogin.getUsername());
        	 model.addAttribute("isAdmin", accountLogin.getRole().getName().equals("ROLE_ADMIN"));
        	 model.addAttribute("roleName",roleName);
        
        	 model.addAttribute("accountName", accountName);
        	 model.addAttribute("employeeName", employeeName);
        	 model.addAttribute("productCode", productCode);
        	 model.addAttribute("productName", productName);
        	 model.addAttribute("customerName", customerName);
        	 model.addAttribute("customerPhone", customerPhone);
        
      
        	 model.addAttribute("statusAllocation", statusAllocation);
        	 model.addAttribute("statusBooking", statusBooking);
        	 return "order/show";
    	} catch (Throwable e) {
    		System.out.println(e.getMessage());
    		model.addAttribute("nameLogin", accountLogin.getUsername());
       	 	model.addAttribute("isAdmin", accountLogin.getRole().getName().equals("ROLE_ADMIN"));
       	 	model.addAttribute("roleName",roleName);
       	 	model.addAttribute("ordersMap", new ArrayList<>());
       	 	model.addAttribute("error", "Lỗi !!! Vui lòng thử lại");
       	    return "order/show";
			
			
		}	
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
