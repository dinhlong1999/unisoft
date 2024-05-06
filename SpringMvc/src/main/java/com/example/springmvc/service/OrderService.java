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
	public List<Map<String, Object>> getListOrder(String username, String employeeName, String codeProduct,
			String nameProduct, String customerName, String phoneNumber, boolean isAdmin, int employeeId,
			LocalDate dateStart, LocalDate dateEnd, int statusAllocation, int statusBooking, int limit, int offset) {
		// TODO Auto-generated method stub
		return orderMapper.getListOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber, isAdmin, employeeId, dateStart, dateEnd, statusAllocation, statusBooking, limit, offset);
	}

	@Override
	public int getTotalRecordByOrder(String username, String employeeName, String codeProduct, String nameProduct,
			String customerName, String phoneNumber, boolean isAdmin, int employeeId, LocalDate dateStart,
			LocalDate dateEnd, int statusAllocation, int statusBooking) {
		// TODO Auto-generated method stub
		return orderMapper.getTotalRecordByOrder(username, employeeName, codeProduct, nameProduct, customerName, phoneNumber, isAdmin, employeeId, dateStart, dateEnd, statusAllocation, statusBooking);
	}

	@Override
	public int insertAndUpdateOrders(int accountId, List<OrdersDTO> ordersDTOs) {
		int count= 0;
		  for(OrdersDTO ordersDTO : ordersDTOs){
              if (ordersDTO.getId().matches("^\\d+$")){
                  int orderId = Integer.parseInt(ordersDTO.getId());
                  Product product = productService.getProductByCodeProduct(ordersDTO.getCodeProduct());
                  int customerId = customerService.getIdCustomerByPhoneNumber(ordersDTO.getPhoneNumber());         
                  int quantityBook = ordersDTO.getQuantityBook();
                  int updateOrders = orderMapper.updateOrder(customerId,product.getId(),quantityBook,ordersDTO.getVersion(),product.getPriceSell(),orderId);
                  if (updateOrders != 1){
                      throw new RuntimeException("Không cap nhat được đơn hàng");
                  }else {
                  	count += 1;
                  }
              }else {
              	 int customerId = customerService.getIdCustomerByPhoneNumber(ordersDTO.getPhoneNumber());
              	 Employee employee = employeeService.getEmployeeByAccountId(accountId);
              	 Product product = productService.getProductByCodeProduct(ordersDTO.getCodeProduct());
              	 LocalDateTime dateStart = LocalDateTime.now();
              	 int statusId = 1;
              	 int insertOrders = orderMapper.insertOrders(customerId, employee.getId(),product.getId(),statusId, dateStart, product.getPriceSell(), ordersDTO.getQuantityBook());
              	 if (insertOrders != 1) {
              		 throw new RuntimeException("Không thêm được đơn hàng");
					}else {
						count +=1;
					}
              }
          }
		return count ;
	}

	@Override
	public String goodsAllocation(int productId, int quantityBook) {
		// TODO Auto-generated method stub
		return orderMapper.goodsAllocation(productId, quantityBook);
	}


}
