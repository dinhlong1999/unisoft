package com.example.projectintern.dto.orderDetail;

import com.example.projectintern.dto.OrderDetailInfo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "orderDetail"
})
@XmlRootElement(name = "saveOrderDetailRequest")
public class SaveOrderDetailRequest {

    @XmlElement(required = true)
    private OrderDetailInfo orderDetail;

    public SaveOrderDetailRequest() {
    }

    public OrderDetailInfo getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailInfo orderDetail) {
        this.orderDetail = orderDetail;
    }
}
