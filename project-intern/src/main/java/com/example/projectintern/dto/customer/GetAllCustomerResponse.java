package com.example.projectintern.dto.customer;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "customers"
})
@XmlRootElement(name = "getAllCustomerResponse")
public class GetAllCustomerResponse {

    @XmlElement(required = true)
    private List<CustomerInfo> customers;

    public GetAllCustomerResponse() {
    }

    public List<CustomerInfo> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerInfo> customers) {
        this.customers = customers;
    }
}
