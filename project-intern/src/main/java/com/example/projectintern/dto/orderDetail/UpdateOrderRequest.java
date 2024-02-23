package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "dateStart","quantityBook", "phoneNumberCustomer","employeeId",
        "productCode","price","orderId"
})
@XmlRootElement(name = "updateOrderRequest")
public class UpdateOrderRequest {

    @XmlElement
    private String dateStart;
    @XmlElement
    private int quantityBook;
    @XmlElement
    private String phoneNumberCustomer;
    @XmlElement
    private int employeeId;
    @XmlElement
    private String productCode;
    @XmlElement
    private int price;
    @XmlElement int orderId;

    public UpdateOrderRequest() {
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public int getQuantityBook() {
        return quantityBook;
    }

    public void setQuantityBook(int quantityBook) {
        this.quantityBook = quantityBook;
    }

    public String getPhoneNumberCustomer() {
        return phoneNumberCustomer;
    }

    public void setPhoneNumberCustomer(String phoneNumberCustomer) {
        this.phoneNumberCustomer = phoneNumberCustomer;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
