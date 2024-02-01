package com.example.projectintern.dto;

import com.example.projectintern.dto.customer.CustomerInfo;
import com.example.projectintern.dto.employee.EmployeeInfo;
import com.example.projectintern.dto.product.ProductInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderDetailInfo",propOrder = {
        "id",
        "employee",
        "product",
        "customer",
        "status",
        "quantityBook",
        "dateStart",
        "dateAllocation",
        "price"
})
public class OrderDetailInfo {
    private int id;
    private EmployeeInfo employee;
    private ProductInfo product;
    private CustomerInfo customer;
    private StatusInfo status;
    private int quantityBook;
    private String dateStart;
    private String dateAllocation;
    private double price;

    public OrderDetailInfo() {
    }

    public OrderDetailInfo(int id,
                           EmployeeInfo employee,
                           ProductInfo product,
                           CustomerInfo customer,
                           StatusInfo status,
                           int quantityBook,
                           String dateStart,
                           String dateAllocation,
                           double price) {
        this.id = id;
        this.employee = employee;
        this.product = product;
        this.customer = customer;
        this.status = status;
        this.quantityBook = quantityBook;
        this.dateStart = dateStart;
        this.dateAllocation = dateAllocation;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EmployeeInfo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeInfo employee) {
        this.employee = employee;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public StatusInfo getStatus() {
        return status;
    }

    public void setStatus(StatusInfo status) {
        this.status = status;
    }

    public int getQuantityBook() {
        return quantityBook;
    }

    public void setQuantityBook(int quantityBook) {
        this.quantityBook = quantityBook;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateAllocation() {
        return dateAllocation;
    }

    public void setDateAllocation(String dateAllocation) {
        this.dateAllocation = dateAllocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
