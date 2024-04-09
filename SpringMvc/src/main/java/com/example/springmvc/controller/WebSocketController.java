/*
 * package com.example.springmvc.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.messaging.handler.annotation.MessageMapping; import
 * org.springframework.messaging.handler.annotation.SendTo; import
 * org.springframework.stereotype.Controller;
 * 
 * import com.example.springmvc.model.Employee; import
 * com.example.springmvc.service.IEmployeeService;
 * 
 * @Controller public class WebSocketController {
 * 
 * 
 * @Autowired private IEmployeeService employeeService;
 * 
 * @MessageMapping("/login")
 * 
 * @SendTo("/topic/employeeStatus") public Employee login(String username) {
 * 
 * Employee employee = employeeService.getEmployeeByUsername(username); int
 * rowEffect = employeeService.updateStatusEmployee(1, employee.getId(),
 * employee.getVersion());
 * 
 * if (rowEffect == 1) { return employee; } return null;
 * 
 * 
 * 
 * }
 * 
 * }
 */