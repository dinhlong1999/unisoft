package com.example.projectintern.dto.customer;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "customerInfo"
})
@XmlRootElement(name = "saveCustomerRequest")
public class SaveCustomerRequest {

    @XmlElement(required = true)
    private CustomerInfo customerInfo;

    public SaveCustomerRequest() {
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
