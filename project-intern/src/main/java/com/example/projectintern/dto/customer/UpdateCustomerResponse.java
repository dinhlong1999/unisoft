package com.example.projectintern.dto.customer;

import com.example.projectintern.dto.ServiceStatus;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "serviceStatus"
})
@XmlRootElement(name = "updateCustomerResponse")
public class UpdateCustomerResponse {

    @XmlElement(required = true)
    private ServiceStatus serviceStatus;

    public UpdateCustomerResponse() {
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
