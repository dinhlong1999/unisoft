package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "customersNoOrder", "errorList"
})
@XmlRootElement(name = "customerNotBuyResponse")
public class CustomerNotBuyResponse {

    @XmlElement
    private List<CustomerNoOrder> customersNoOrder;

    @XmlElement
    private List<String> errorList;


    public CustomerNotBuyResponse() {
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<CustomerNoOrder> getCustomersNoOrder() {
        return customersNoOrder;
    }

    public void setCustomersNoOrder(List<CustomerNoOrder> customersNoOrder) {
        this.customersNoOrder = customersNoOrder;
    }
}
