package com.example.projectintern.dto.customer;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "nameCustomer","phoneNumber","limit","page"
})
@XmlRootElement(name = "getAllCustomerRequest")
public class GetAllCustomerRequest {

    @XmlElement(required = true)
    private String nameCustomer;
    @XmlElement(required = true)
    private String phoneNumber;
    @XmlElement(required = true)
    private int limit;
    @XmlElement(required = true)
    private int page;

    public GetAllCustomerRequest() {
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
