package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "customersNoOrder"
})
@XmlRootElement(name = "customerNotBuyResponse")
public class CustomerNotBuyResponse {

    @XmlElement
    private List<CustomerNoOrder> customersNoOrder;

    public CustomerNotBuyResponse() {
    }

    public List<CustomerNoOrder> getCustomersNoOrder() {
        return customersNoOrder;
    }

    public void setCustomersNoOrder(List<CustomerNoOrder> customersNoOrder) {
        this.customersNoOrder = customersNoOrder;
    }
}
