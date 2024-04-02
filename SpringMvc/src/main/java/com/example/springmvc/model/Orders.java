package com.example.springmvc.model;


import java.time.LocalDateTime;

public class Orders {

    private int id;

    private int employeeId;

    private int productId;

    private int customerId;

    private int statusId;

    private int quantityBook;

    private LocalDateTime dateStart;

    private LocalDateTime dateAllocation;
    private double price;

    private int version;
    private boolean flag;


    public Orders() {
    }

    public Orders(int id,
                  int employeeId,
                  int productId,
                  int customerId,
                  int statusId,
                  int quantityBook,
                  LocalDateTime dateStart,
                  boolean flag,
                  LocalDateTime dateAllocation,
                  double price,
                  int version) {
        this.id = id;
        this.employeeId = employeeId;
        this.productId = productId;
        this.customerId = customerId;
        this.statusId = statusId;
        this.quantityBook = quantityBook;
        this.dateStart = dateStart;
        this.flag = flag;
        this.dateAllocation=dateAllocation;
        this.price = price;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getQuantityBook() {
        return quantityBook;
    }

    public void setQuantityBook(int quantityBook) {
        this.quantityBook = quantityBook;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateAllocation() {
        return dateAllocation;
    }

    public void setDateAllocation(LocalDateTime dateAllocation) {
        this.dateAllocation = dateAllocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}