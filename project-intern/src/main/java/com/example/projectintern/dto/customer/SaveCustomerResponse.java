package com.example.projectintern.dto.customer;

import com.example.projectintern.dto.ServiceStatus;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "serviceStatus"
})
@XmlRootElement(name = "saveCustomerResponse")
public class SaveCustomerResponse {

    @XmlElement(required = true)
    private ServiceStatus serviceStatus;

    public SaveCustomerResponse() {
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
