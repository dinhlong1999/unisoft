package com.example.springmvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmvc.common.CheckLogin;
import com.example.springmvc.common.Paging;
import com.example.springmvc.service.AuthenticationService;
import com.example.springmvc.service.IAnalystService;

@Controller
@RequestMapping("/analyst")
public class AnalystController {
	
	@Autowired
	private IAnalystService analystService;
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@GetMapping("/list")
	public String anaLystProduct(@RequestParam(required = false,defaultValue = "0") int page,
								 @RequestParam(required = false,defaultValue = "") String orderDayBegin,
								 @RequestParam(required = false,defaultValue = "") String orderDayEnd,Model model
								 ) {
		String accountNameLogin = "";
		boolean isAdmin = false;
		List<Map<String, Object>> customerNoOrder;
		int totalRowByCustomerNoOrder;
		List<Map<String,Object>> productsBestSeller;
		int totalRowByProductBestSeller;
		List<Map<String, Object>> productsNoSell ;
		int getTotalRowByProductNoSell;
		try {
			if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
 				return "redirect:/logout";
 		    }
			accountNameLogin = authenticationService.getAccountLogin().getUsername();
			isAdmin = authenticationService.getAccountLogin().getRole().getName().equals("ROLE_ADMIN");
			int limit = 4;
	    	if (page != 0) {
	    		page = page - 1;
			}

			if (orderDayBegin.isEmpty() || orderDayBegin.length() < 10  ) {
				orderDayBegin = "2000-01-01";
				model.addAttribute("orderDayBegin", "");
			}else if (orderDayBegin.length() == 10) {
				model.addAttribute("orderDayBegin", orderDayBegin);
			} else {
				orderDayBegin = "9999-01-01";
				model.addAttribute("orderDayBegin", "");
			}
			if (orderDayEnd.isEmpty() || orderDayEnd.length() > 10  ) {
				orderDayEnd = "9999-01-01";
				model.addAttribute("orderDayEnd", "");
			}else if (orderDayEnd.length() == 10) {
				model.addAttribute("orderDayEnd", orderDayEnd);
			} else {
				orderDayEnd = "2000-01-01";
				model.addAttribute("orderDayEnd", "");
			}
			
			
			
		
			
			
			//Danh sách khách hàng không mua sản phẩm nào
			customerNoOrder = analystService.getCustomerNoOrders(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit, limit*page);
			totalRowByCustomerNoOrder = analystService.getTotalRowByCustomerNoOrders(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
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
			
		
			model.addAttribute("nameLogin", accountNameLogin);
			model.addAttribute("isAdmin", isAdmin);
			
			//Danh sách sản phẩm bán chạy
			productsBestSeller = analystService.getProductBestSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit, limit*page);
			totalRowByProductBestSeller = analystService.getTotalRowByProductBestSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
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
			
			
			// Danh sách sản phẩm không có đơn đặt hàng
			productsNoSell = analystService.getProductNoSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit, limit*page);
			getTotalRowByProductNoSell = analystService.getTotalRowByProductNoSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));

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
		} catch (Exception e) {
			System.err.println(e.getMessage());
			customerNoOrder = new ArrayList<>();
			productsBestSeller = new ArrayList<>();
			productsNoSell = new ArrayList<>();
		}
		model.addAttribute("customerNoOrder", customerNoOrder);
		model.addAttribute("productsBestSeller", productsBestSeller);
		model.addAttribute("productsNoSell", productsNoSell);
		return "analyst";

	}
	

}
