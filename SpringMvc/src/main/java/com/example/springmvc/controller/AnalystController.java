package com.example.springmvc.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
								 @RequestParam(required = false,defaultValue = "") String dateStart,
								 @RequestParam(required = false,defaultValue = "") String dateEnd,Model model) {
		int limit = 4;
    	if (page != 0) {
    		page = page - 1;
		}

		if (dateStart.isEmpty()) {
			dateStart = "2000-01-01";
		}else {
			model.addAttribute("dateStart", dateStart);
		};
		if (dateEnd.isEmpty()) {
			dateEnd = "9999-01-01";
		}else {
			model.addAttribute("dateEnd",dateEnd);
		}
		Account account = getAccountLogin();
		
		//Danh sách khách hàng không mua sản phẩm nào
		List<Map<String, Object>> customerNoOrder = analystService.getCustomerNoOrders(LocalDate.parse(dateStart), LocalDate.parse(dateEnd), limit, limit*page);
		int totalRowByCustomerNoOrder = analystService.getTotalRowByCustomerNoOrders(LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
		double temp = (double)  totalRowByCustomerNoOrder / limit ;
		int totalPageByCustomerNoOrder = (int) Math.ceil(temp);
		
		int maxVisitablePages = 10; // Số trang tối đa hiển thị
		int adjacentPages = 2; // số trang bên cạnh trang hiện tại
		int startPage;
		int endPage;
		boolean showStartEllipsis = false; // Dấu ... đầu
		boolean showEndEllipsis = false; // Dấu ... cuối
		if (totalPageByCustomerNoOrder <= maxVisitablePages) {
			startPage = 1;
			endPage = totalPageByCustomerNoOrder;
		} else {
			if (page <= maxVisitablePages - adjacentPages) {
				startPage = 1;
				endPage = maxVisitablePages;
				showEndEllipsis = true;
			} else if (page >= totalPageByCustomerNoOrder - adjacentPages) {
				startPage = totalPageByCustomerNoOrder - maxVisitablePages + 1;
				endPage = totalPageByCustomerNoOrder;
				showStartEllipsis = true;
			} else {
				startPage = page - adjacentPages;
				endPage = page + adjacentPages;
				showStartEllipsis = true;
				showEndEllipsis = true;
			}
		}
		
		model.addAttribute("totalPageByCustomerNoOrder", totalPageByCustomerNoOrder);
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		model.addAttribute("startPageCustomerNoOrder", startPage);
		model.addAttribute("endPageCustomerNoOrder", endPage);
		model.addAttribute("showStartEllipsisCustomerNoOrder", showStartEllipsis);
		model.addAttribute("showEndEllipsisCustomerNoOrder", showEndEllipsis);
		
		model.addAttribute("customerNoOrder", customerNoOrder);
		model.addAttribute("nameLogin", account.getUsername());
		
		
		//Danh sách sản phẩm bán chạy
		List<Map<String,Object>> productsBestSeller = analystService.getProductBestSeller(LocalDate.parse(dateStart), LocalDate.parse(dateEnd), limit, limit*page);
		int totalRowByProductBestSeller = analystService.getTotalRowByProductBestSeller(LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
		double rowProductTemp = (double) totalRowByProductBestSeller / limit;
		int totalPageByProductBestSeller = (int) Math.ceil(rowProductTemp);
		
		
		int startPageProductBestSeller;
		int endPageProductBestSeller;
		boolean showStartEllipsisProductBestSeller = false; // Dấu ... đầu
		boolean showEndEllipsisProductBestSeller = false; // Dấu ... cuối
		if (totalPageByProductBestSeller <= maxVisitablePages) {
			startPageProductBestSeller = 1;
			endPageProductBestSeller = totalPageByProductBestSeller;
		} else {
			if (page <= maxVisitablePages - adjacentPages) {
				startPageProductBestSeller = 1;
				endPageProductBestSeller = maxVisitablePages;
				showEndEllipsisProductBestSeller = true;
			} else if (page >= totalPageByProductBestSeller - adjacentPages) {
				startPageProductBestSeller = totalPageByProductBestSeller - maxVisitablePages + 1;
				endPageProductBestSeller = totalPageByProductBestSeller;
				showStartEllipsisProductBestSeller = true;
			} else {
				startPageProductBestSeller = page - adjacentPages;
				endPageProductBestSeller = page + adjacentPages;
				showStartEllipsisProductBestSeller = true;
				showEndEllipsisProductBestSeller = true;
			}
		}
		model.addAttribute("totalPageByProductBestSeller", totalPageByProductBestSeller);
		model.addAttribute("startPageProductBestSeller", startPageProductBestSeller);
		model.addAttribute("endPageProductBestSeller", endPageProductBestSeller);
		model.addAttribute("showStartEllipsisProductBestSeller", showStartEllipsisProductBestSeller);
		model.addAttribute("showEndEllipsisProductBestSeller", showEndEllipsisProductBestSeller);
		
		model.addAttribute("productsBestSeller", productsBestSeller);
		
		// Danh sách sản phẩm không có đơn đặt hàng
		List<Map<String, Object>> productsNoSell = analystService.getProductNoSeller(LocalDate.parse(dateStart), LocalDate.parse(dateEnd), limit, limit*page);
		int getTotalRowByProductNoSell = analystService.getTotalRowByProductNoSeller(LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
		double rowProductNoSellTemp = (double) getTotalRowByProductNoSell / limit;
		int totalPageByProductNoSell = (int) Math.ceil(rowProductNoSellTemp);
		
		int startPageProductNoSeller;
		int endPageProductNoSeller;
		boolean showStartEllipsisProductNoSeller = false; // Dấu ... đầu
		boolean showEndEllipsisProductNoSeller = false; // Dấu ... cuối
		
		if (totalPageByProductNoSell <= maxVisitablePages) {
			startPageProductNoSeller = 1;
			endPageProductNoSeller = totalPageByProductNoSell;
		} else {
			if (page <= maxVisitablePages - adjacentPages) {
				startPageProductNoSeller = 1;
				endPageProductNoSeller = maxVisitablePages;
				showEndEllipsisProductNoSeller = true;
			} else if (page >= totalPageByProductNoSell - adjacentPages) {
				startPageProductNoSeller = totalPageByProductNoSell - maxVisitablePages + 1;
				endPageProductNoSeller = totalPageByProductNoSell;
				showStartEllipsisProductNoSeller = true;
			} else {
				startPageProductNoSeller = page - adjacentPages;
				endPageProductNoSeller = page + adjacentPages;
				showStartEllipsisProductNoSeller = true;
				showEndEllipsisProductNoSeller = true;
			}
		}
		
		model.addAttribute("totalPageByProductNoSell", totalPageByProductNoSell);
		model.addAttribute("startPageProductNoSeller", startPageProductNoSeller);
		model.addAttribute("endPageProductNoSeller", endPageProductNoSeller);
		model.addAttribute("showStartEllipsisProductNoSeller", showStartEllipsisProductNoSeller);
		model.addAttribute("showEndEllipsisProductNoSeller", showEndEllipsisProductNoSeller);
		
		model.addAttribute("productsNoSell", productsNoSell);

		return "analyst";
		

	}
	

}
