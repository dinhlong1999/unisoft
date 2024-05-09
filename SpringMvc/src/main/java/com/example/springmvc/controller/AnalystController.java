package com.example.springmvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.springmvc.common.Paging;
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
import com.example.springmvc.service.IAnalystService;

@Controller
@RequestMapping("/analyst")
public class AnalystController {
	
	@Autowired
	private IAnalystService analystService;
	
	@Autowired
	private IAccountService accountService;

	private Account getAccountLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String usernameLogin = authentication.getName();
			Account account = accountService.getAccountByUsername(usernameLogin);
			return account;
		}
		return null;
	}
	
	@GetMapping("/list")
	public String anaLystProduct(@RequestParam(required = false,defaultValue = "0") int page,
								 @RequestParam(required = false,defaultValue = "") String orderDayBegin,
								 @RequestParam(required = false,defaultValue = "") String orderDayEnd,Model model) {
		int limit = 4;
    	if (page != 0) {
    		page = page - 1;
		}

		if (orderDayBegin.isEmpty()) {
			orderDayBegin = "2000-01-01";
			model.addAttribute("orderDayBegin", "");
		}else {
			model.addAttribute("orderDayBegin", orderDayBegin);
		};
		if (orderDayEnd.isEmpty()) {
			model.addAttribute("orderDayEnd","");
			orderDayEnd = "9999-01-01";
		}else {
			model.addAttribute("orderDayEnd",orderDayEnd);
		}
		
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
		Account account = getAccountLogin();
		
		//Danh sách khách hàng không mua sản phẩm nào
		List<Map<String, Object>> customerNoOrder;
		int totalRowByCustomerNoOrder;
		try {
			customerNoOrder = analystService.getCustomerNoOrders(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit, limit*page);
			totalRowByCustomerNoOrder = analystService.getTotalRowByCustomerNoOrders(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
		} catch (Throwable e) {
			customerNoOrder = new ArrayList<>();
			totalRowByCustomerNoOrder = 0;
		}
		
		double temp = (double)  totalRowByCustomerNoOrder / limit ;
		int totalPageByCustomerNoOrder = (int) Math.ceil(temp);

		Map<String,Object> pagination = Paging.handlePaging(page,totalPageByCustomerNoOrder);
		int startPage = (int) pagination.get("startPage");
		int endPage = (int) pagination.get("endPage");
		boolean showStartEllipsis = (boolean) pagination.get("showStartEllipsis");
		boolean showEndEllipsis = (boolean) pagination.get("showEndEllipsis");

		model.addAttribute("totalPageByCustomerNoOrder", totalPageByCustomerNoOrder);
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		model.addAttribute("startPageCustomerNoOrder", startPage);
		model.addAttribute("endPageCustomerNoOrder", endPage);
		model.addAttribute("showStartEllipsisCustomerNoOrder", showStartEllipsis);
		model.addAttribute("showEndEllipsisCustomerNoOrder", showEndEllipsis);
		
		model.addAttribute("customerNoOrder", customerNoOrder);
		model.addAttribute("nameLogin", account.getUsername());
		model.addAttribute("isAdmin", account.getRole().getName().equals("ROLE_ADMIN"));
		
		//Danh sách sản phẩm bán chạy
		List<Map<String,Object>> productsBestSeller;
		int totalRowByProductBestSeller;
		try {
			productsBestSeller = analystService.getProductBestSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit, limit*page);
			totalRowByProductBestSeller = analystService.getTotalRowByProductBestSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
		} catch (Throwable e) {
			productsBestSeller = new ArrayList<>();
			System.out.println(e.getMessage());
			totalRowByProductBestSeller = 0;
		}
	    
		double rowProductTemp = (double) totalRowByProductBestSeller / limit;
		int totalPageByProductBestSeller = (int) Math.ceil(rowProductTemp);
		
		Map<String,Object> pagingProductBestSeller = Paging.handlePaging(page,totalPageByProductBestSeller);
		int startPageProductBestSeller = (int) pagingProductBestSeller.get("startPage");
		int endPageProductBestSeller = (int) pagingProductBestSeller.get("endPage");
		boolean showStartEllipsisProductBestSeller = (boolean) pagingProductBestSeller.get("showStartEllipsis");
		boolean showEndEllipsisProductBestSeller = (boolean) pagingProductBestSeller.get("showEndEllipsis");
		model.addAttribute("totalPageByProductBestSeller", totalPageByProductBestSeller);
		model.addAttribute("startPageProductBestSeller", startPageProductBestSeller);
		model.addAttribute("endPageProductBestSeller", endPageProductBestSeller);
		model.addAttribute("showStartEllipsisProductBestSeller", showStartEllipsisProductBestSeller);
		model.addAttribute("showEndEllipsisProductBestSeller", showEndEllipsisProductBestSeller);
		
		model.addAttribute("productsBestSeller", productsBestSeller);
		
		// Danh sách sản phẩm không có đơn đặt hàng
		List<Map<String, Object>> productsNoSell ;
		int getTotalRowByProductNoSell;
		try {
			productsNoSell = analystService.getProductNoSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit, limit*page);
			getTotalRowByProductNoSell = analystService.getTotalRowByProductNoSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			productsNoSell = new ArrayList<>();
			getTotalRowByProductNoSell = 0;
		}
		
		double rowProductNoSellTemp = (double) getTotalRowByProductNoSell / limit;
		int totalPageByProductNoSell = (int) Math.ceil(rowProductNoSellTemp);

		Map<String,Object> pagingProductNoSell = Paging.handlePaging(page,totalPageByProductNoSell);
		int startPageProductNoSeller = (int) pagingProductNoSell.get("startPage");
		int endPageProductNoSeller = (int) pagingProductNoSell.get("endPage");
		boolean showStartEllipsisProductNoSeller = (boolean) pagingProductNoSell.get("showStartEllipsis");
		boolean showEndEllipsisProductNoSeller = (boolean) pagingProductNoSell.get("showEndEllipsis");
		
		model.addAttribute("totalPageByProductNoSell", totalPageByProductNoSell);
		model.addAttribute("startPageProductNoSeller", startPageProductNoSeller);
		model.addAttribute("endPageProductNoSeller", endPageProductNoSeller);
		model.addAttribute("showStartEllipsisProductNoSeller", showStartEllipsisProductNoSeller);
		model.addAttribute("showEndEllipsisProductNoSeller", showEndEllipsisProductNoSeller);
		
		model.addAttribute("productsNoSell", productsNoSell);

		return "analyst";
		

	}
	

}
