package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iOrderDetailDTO",propOrder = {
        "dateStart",
        "codeProduct","priceBuy",
        "quantityBook","customerName",
        "customerPhoneNumber","customerAddress",
        "statusName","accountName",
        "employeeName","dateAllocation"

})
public class OrderDTO {
    @XmlElement(required = true)
    private String dateStart;
    @XmlElement(required = true)
    private String codeProduct;
    @XmlElement(required = true)
    private double priceBuy;
    @XmlElement(required = true)
    private int quantityBook;
    @XmlElement(required = true)
    private String customerName;
    @XmlElement(required = true)
    private String customerPhoneNumber;
    @XmlElement(required = true)
    private String customerAddress;
    @XmlElement(required = true)
    private String statusName;
    @XmlElement(required = true)
    private String accountName;
    @XmlElement(required = true)
    private String employeeName;
    @XmlElement(required = true)
    private String dateAllocation;

    public OrderDTO() {
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }


    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public int getQuantityBook() {
        return quantityBook;
    }

    public void setQuantityBook(int quantityBook) {
        this.quantityBook = quantityBook;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDateAllocation() {
        return dateAllocation;
    }

    public void setDateAllocation(String dateAllocation) {
        this.dateAllocation = dateAllocation;
    }
}
