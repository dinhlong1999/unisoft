package com.example.projectintern.dto.product;

import com.example.projectintern.dto.ServiceStatus;
import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "serviceStatus"
})
@XmlRootElement(name = "updateProductResponse")
public class UpdateProductResponse {

    private ServiceStatus serviceStatus;

    public UpdateProductResponse() {
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
