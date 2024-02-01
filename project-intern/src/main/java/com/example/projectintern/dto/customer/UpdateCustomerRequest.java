package com.example.projectintern.dto.customer;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "customerInfo"
})
@XmlRootElement(name = "updateCustomerRequest")
public class UpdateCustomerRequest {

    @XmlElement(required = true)
    private CustomerInfo customerInfo;

    public UpdateCustomerRequest() {
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
