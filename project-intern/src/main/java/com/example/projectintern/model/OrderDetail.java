package com.example.projectintern.model;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "quantity_book",nullable = false)
    private int quantityBook;
    @Column(name = "date_start" ,nullable = false)
    private LocalDate dateStart;
    @Column(name = "date_allocation")
    private LocalDate dateAllocation;
    private double price;

    @Column(columnDefinition = "bit(1) default 0",nullable = false)
    private boolean flag;

    public OrderDetail() {
    }

    public OrderDetail(int id,
                       Employee employee,
                       Product product,
                       Customer customer,
                       Status status,
                       int quantityBook,
                       LocalDate dateStart,
                       boolean flag,
                       LocalDate dateAllocation,
                       double price) {
        this.id = id;
        this.employee = employee;
        this.product = product;
        this.customer = customer;
        this.status = status;
        this.quantityBook = quantityBook;
        this.dateStart = dateStart;
        this.flag = flag;
        this.dateAllocation=dateAllocation;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getQuantityBook() {
        return quantityBook;
    }

    public void setQuantityBook(int quantityBook) {
        this.quantityBook = quantityBook;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }


    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public LocalDate getDateAllocation() {
        return dateAllocation;
    }

    public void setDateAllocation(LocalDate dateAllocation) {
        this.dateAllocation = dateAllocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
