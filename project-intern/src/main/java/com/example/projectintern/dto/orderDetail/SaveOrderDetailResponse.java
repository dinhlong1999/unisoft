package com.example.projectintern.dto.orderDetail;

import com.example.projectintern.dto.ServiceStatus;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "serviceStatus"
})
@XmlRootElement(name = "saveOrderDetailResponse")
public class SaveOrderDetailResponse {

    @XmlElement(required = true)
    private ServiceStatus serviceStatus;

    public SaveOrderDetailResponse() {
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
