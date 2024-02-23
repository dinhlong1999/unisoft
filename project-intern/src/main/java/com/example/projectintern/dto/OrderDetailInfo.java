package com.example.projectintern.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderDetailInfo",propOrder = {
        "id",
        "codeProduct",
        "customerPhoneNumber",
        "quantityBook",
        "dateStart",
        "price"
})
public class OrderDetailInfo {
   @XmlElement(required = true)
    private int id;
   @XmlElement(required = true)
    private String codeProduct;
   @XmlElement(required = true)
    private String customerPhoneNumber;

   @XmlElement(required = true)
    private int quantityBook;
   @XmlElement(required = true)
    private String dateStart;
   @XmlElement(required = true)
    private double price;

    public OrderDetailInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetailInfo{" +
                "id=" + id +
                ", codeProduct='" + codeProduct + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", quantityBook=" + quantityBook +
                ", dateStart=" + dateStart +
                ", price=" + price +
                '}';
    }
}
