package com.example.projectintern.dto.employee;

import com.example.projectintern.dto.ServiceStatus;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "serviceStatus"
})
@XmlRootElement(name = "saveEmployeeResponse")
public class SaveEmployeeResponse {
    @XmlElement(required = true)
    private ServiceStatus serviceStatus;

    public SaveEmployeeResponse() {
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
