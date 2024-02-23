package com.example.projectintern.dto.orderDetail;

import com.example.projectintern.dto.OrderDetailInfo;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "orderDetail"
})
@XmlRootElement(name = "saveOrderDetailRequest")
public class SaveOrderDetailRequest {

    @XmlElement(required = true)
    private List<OrderDetailInfo> orderDetail;

    public SaveOrderDetailRequest() {
    }

    public List<OrderDetailInfo> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailInfo> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
