package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "orderDetailDTOs"
})
@XmlRootElement(name = "getOrderDetailResponse")
public class GetOrderDetailResponse {

    @XmlElement(required = true)
    private List<OrderDTO> orderDetailDTOs;

    public GetOrderDetailResponse() {
    }

    public List<OrderDTO> getOrderDetailDTOs() {
        return orderDetailDTOs;
    }

    public void setOrderDetailDTOs(List<OrderDTO> orderDetailDTOs) {
        this.orderDetailDTOs = orderDetailDTOs;
    }
}
