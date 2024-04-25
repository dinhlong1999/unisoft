package com.example.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.dto.AnalystDTO;

@RestController
@RequestMapping("/api/analyst")
public class AnaLystRestController {

	@GetMapping("/customernoorder")
	public AnalystDTO getCustomerNoOrder(@RequestParam int page,
										 @RequestParam(required = false,defaultValue = "") String dateStart,
										 @RequestParam(required = false,defaultValue = "") String dateEnd) {
		
		AnalystDTO analystDTO = new AnalystDTO();
		int limit = 4;
		if (page != 0) {
			page = page -1;
		}
		if (dateStart.isEmpty()) {
			dateStart = "2000-01-01";
		}else {
			analystDTO.setDateStart(dateStart);
		};
		if (dateEnd.isEmpty()) {
			dateEnd = "9999-01-01";
		}else {
			analystDTO.setDateEnd(dateEnd);
		}
		
		return analystDTO;
	}
	
}

