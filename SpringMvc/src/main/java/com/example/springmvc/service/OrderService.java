package com.example.springmvc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springmvc.dto.OrdersDTO;
import com.example.springmvc.model.Employee;
import com.example.springmvc.model.Product;
import com.example.springmvc.repository.OrderMapper;

@Service
@Transactional
public class OrderService implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IEmployeeService employeeService;

	@Override
	public List<Map<String, Object>> getListOrder(String accountName, String employeeName, String productCode,
												  String productName, String customerName, String customerPhone, boolean isAdmin, int employeeId,
												  LocalDate orderDayBegin, LocalDate orderDayEnd, int statusAllocation, int statusBooking, int limit, int offset) {
		
		return orderMapper.getListOrder(accountName, employeeName, productCode, productName, customerName, customerPhone, 
										isAdmin, employeeId, orderDayBegin, orderDayEnd, statusAllocation, statusBooking, limit, offset);
	}
	
	@Override
	public int getTotalRecordByOrder(String accountName, String employeeName, String productCode, String productName,
									 String customerName, String customerPhone, boolean isAdmin, int employeeId, LocalDate orderDayBegin,
									 LocalDate orderDayEnd, int statusAllocation, int statusBooking) {
		
		return orderMapper.getTotalRecordByOrder(accountName, employeeName, productCode, productName, customerName, 
												 customerPhone, isAdmin, employeeId, orderDayBegin, orderDayEnd, statusAllocation, statusBooking);
	}

	@Override
	public boolean insertAndUpdateOrders(int accountId, List<OrdersDTO> ordersDTOs) {
		  for(OrdersDTO ordersDTO : ordersDTOs){
              if (ordersDTO.getId().matches("^\\d+$")){
                  int orderId = Integer.parseInt(ordersDTO.getId());
                  Product product = productService.getProductByCodeProduct(ordersDTO.getProductCode());
                  int customerId = customerService.getIdCustomerByPhoneNumber(ordersDTO.getCustomerPhone());
                  int quantityBook = ordersDTO.getQuantity();
                  int updateOrders = orderMapper.updateOrder(customerId,product.getId(),quantityBook,ordersDTO.getVersion(),product.getPriceSell(),orderId);
                  if (updateOrders != 1){
                      throw new RuntimeException("Không cap nhat được đơn hàng");
                  }
              }else {
              	 int customerId = customerService.getIdCustomerByPhoneNumber(ordersDTO.getCustomerPhone());
              	 Employee employee = employeeService.getEmployeeByAccountId(accountId);
              	 Product product = productService.getProductByCodeProduct(ordersDTO.getProductCode());
              	 LocalDateTime dateStart = LocalDateTime.now();
              	 int statusId = 1;
              	 int insertOrders = orderMapper.insertOrders(customerId, employee.getId(),product.getId(),statusId, dateStart, product.getPriceSell(), ordersDTO.getQuantity());
              	 if (insertOrders != 1) {
              		 throw new RuntimeException("Không thêm được đơn hàng");
				 }
              }
          }
		return true ;
	}

	@Override
	public String goodsAllocation(int productId, int quantityBook) {
		
		return orderMapper.goodsAllocation(productId, quantityBook);
	}


}
