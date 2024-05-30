package com.example.springmvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.springmvc.common.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.dto.AnalystDTO;
import com.example.springmvc.service.IAnalystService;

@RestController
@RequestMapping("/api/analyst")
public class AnaLystRestController {
	
	@Autowired
	private IAnalystService analystService;

	@GetMapping("/customernoorder")
	public AnalystDTO getCustomerNoOrder(@RequestParam int page,
										 @RequestParam(required = false,defaultValue = "") String orderDayBegin,
										 @RequestParam(required = false,defaultValue = "") String orderDayEnd) {
		AnalystDTO analystDTO = new AnalystDTO();
		try {
			int limit = 4;
			if (page != 0) {
				page = page -1;
			}
			if (orderDayBegin.isEmpty()) {
				orderDayBegin = "2000-01-01";
				analystDTO.setOrderDayBegin("");
			}else {
				analystDTO.setOrderDayBegin(orderDayBegin);
			};
			if (orderDayEnd.isEmpty()) {
				orderDayEnd = "9999-01-01";
				analystDTO.setOrderDayEnd("");
			}else {
				analystDTO.setOrderDayEnd(orderDayEnd);
			}
			
			List<Map<String, Object>> getCustomerNoOrder = analystService.getCustomerNoOrders(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit,limit*page);
			analystDTO.setCustomerList(getCustomerNoOrder);
			
			int totalRowByCustomerNoOrder = analystService.getTotalRowByCustomerNoOrders(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
			double temp = (double)  totalRowByCustomerNoOrder / limit ;
			int totalPageByCustomerNoOrder = (int) Math.ceil(temp);
			Map<String,Object> handlePaging = Paging.handlePaging(page,totalPageByCustomerNoOrder);
			analystDTO.setStartPage((int) handlePaging.get("startPage"));
			analystDTO.setEndPage((int) handlePaging.get("endPage"));
			analystDTO.setShowStartEllipsis((boolean) handlePaging.get("showStartEllipsis"));
			analystDTO.setShowEndEllipsis((boolean) handlePaging.get("showEndEllipsis"));

			analystDTO.setPage(page);
			analystDTO.setTotalPage(totalPageByCustomerNoOrder);
			analystDTO.setLimit(limit);

			return analystDTO;
		} catch (Exception e) {
			analystDTO.setMessageError("Lỗi !!! Vui lòng thử lại");
			analystDTO.setCustomerList(new ArrayList<>());
			return analystDTO;
		}
		
	}
	
	@GetMapping("/productBestSeller")
	public AnalystDTO getProductBestSeller(@RequestParam int page,
										 @RequestParam(required = false,defaultValue = "") String orderDayBegin,
										 @RequestParam(required = false,defaultValue = "") String orderDayEnd) {
		AnalystDTO analystDTO = new AnalystDTO();
		try {
			int limit = 4;
			if (page != 0) {
				page = page -1;
			}
			if (orderDayBegin.isEmpty()) {
				orderDayBegin = "2000-01-01";
				analystDTO.setOrderDayBegin("");
			}else {
				analystDTO.setOrderDayBegin(orderDayBegin);
			};
			if (orderDayEnd.isEmpty()) {
				orderDayEnd = "9999-01-01";
				analystDTO.setOrderDayEnd("");
			}else {
				analystDTO.setOrderDayEnd(orderDayEnd);
			}
			List<Map<String,Object>> productBestSellerList = analystService.getProductBestSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit,limit*page);
			analystDTO.setProductList(productBestSellerList);

			int totalRecordByProductBestSeller = analystService.getTotalRowByProductBestSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
			double temp = (double)  totalRecordByProductBestSeller / limit ;
			int totalPageByProductBestSeller = (int) Math.ceil(temp);
			Map<String,Object> handlePaging =Paging.handlePaging(page,totalPageByProductBestSeller);
			analystDTO.setStartPage((int) handlePaging.get("startPage"));
			analystDTO.setEndPage((int) handlePaging.get("endPage"));
			analystDTO.setShowStartEllipsis((boolean) handlePaging.get("showStartEllipsis"));
			analystDTO.setShowEndEllipsis((boolean) handlePaging.get("showEndEllipsis"));
			analystDTO.setPage(page);
			analystDTO.setTotalPage(totalPageByProductBestSeller);
			analystDTO.setLimit(limit);
			return analystDTO;

		}catch (Throwable e){
			System.out.println(e.getMessage());
			analystDTO.setProductList(new ArrayList<>());
			analystDTO.setMessageError("Lỗi !!! Vui lòng thử lại");
			return analystDTO;
		}

	};
	@GetMapping("/productNoSell")
	public AnalystDTO getProductNoSell(@RequestParam int page,
			 @RequestParam(required = false,defaultValue = "") String orderDayBegin,
			 @RequestParam(required = false,defaultValue = "") String orderDayEnd) {
		
		AnalystDTO analystDTO = new AnalystDTO();
		try {
			int limit = 4;
			if (page != 0) {
				page = page -1;
			}
			if (orderDayBegin.isEmpty()) {
				orderDayBegin = "2000-01-01";
				analystDTO.setOrderDayBegin("");
			}else {
				analystDTO.setOrderDayBegin(orderDayBegin);
			};
			if (orderDayEnd.isEmpty()) {
				orderDayEnd = "9999-01-01";
				analystDTO.setOrderDayEnd("");
			}else {
				analystDTO.setOrderDayEnd(orderDayEnd);
			}
			List<Map<String, Object>> productNoSell = analystService.getProductNoSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd), limit,limit*page);
			analystDTO.setProductList(productNoSell);

			int totalRecordByProductNoSell = analystService.getTotalRowByProductNoSeller(LocalDate.parse(orderDayBegin), LocalDate.parse(orderDayEnd));
			double temp = (double)  totalRecordByProductNoSell / limit ;
			int totalPageByProductNoSell = (int) Math.ceil(temp);
			Map<String,Object> handlePagingProductNoSell =Paging.handlePaging(page,totalPageByProductNoSell);
			analystDTO.setStartPage((int) handlePagingProductNoSell.get("startPage"));
			analystDTO.setEndPage((int) handlePagingProductNoSell.get("endPage"));
			analystDTO.setShowStartEllipsis((boolean) handlePagingProductNoSell.get("showStartEllipsis"));
			analystDTO.setShowEndEllipsis((boolean) handlePagingProductNoSell.get("showEndEllipsis"));
			analystDTO.setPage(page);
			analystDTO.setTotalPage(totalPageByProductNoSell);
			analystDTO.setLimit(limit);
			return analystDTO;

		}catch (Throwable e){
			System.out.println(e.getMessage());
			analystDTO.setProductList(new ArrayList<>());
			analystDTO.setMessageError("Lỗi !!! Vui lòng thử lại");
			return analystDTO;
		}

		
	}


	
}

