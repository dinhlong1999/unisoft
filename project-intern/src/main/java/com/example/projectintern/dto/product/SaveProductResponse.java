package com.example.projectintern.dto.product;

import com.example.projectintern.dto.ServiceStatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder ={
        "serviceStatus"
})
@XmlRootElement(name = "serviceStatus")
public class SaveProductResponse {
    private ServiceStatus serviceStatus;

    public SaveProductResponse() {
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
