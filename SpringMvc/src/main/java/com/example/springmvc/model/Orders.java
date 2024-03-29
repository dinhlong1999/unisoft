package com.example.springmvc.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Orders {
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
    private LocalDateTime dateStart;
    @Column(name = "date_allocation")
    private LocalDateTime dateAllocation;
    private double price;

    @Column(columnDefinition = "int default 1",nullable = false)
    private int version;
    @Column(columnDefinition = "bit(1) default 0",nullable = false)
    private boolean flag;


    public Orders() {
    }

    public Orders(int id,
                  Employee employee,
                  Product product,
                  Customer customer,
                  Status status,
                  int quantityBook,
                  LocalDateTime dateStart,
                  boolean flag,
                  LocalDateTime dateAllocation,
                  double price,
                  int version) {
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
        this.version = version;
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

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }


    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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
}