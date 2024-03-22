package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
        "orderDetailDTOs","errorList"
})
@XmlRootElement(name = "getOrderDetailResponse")
public class GetOrderDetailResponse {

    @XmlElement(required = true)
    private List<OrderDTO> orderDetailDTOs;

    @XmlElement(required = true)
    private List<String> errorList;

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public GetOrderDetailResponse() {
    }

    public List<OrderDTO> getOrderDetailDTOs() {
        return orderDetailDTOs;
    }

    public void setOrderDetailDTOs(List<OrderDTO> orderDetailDTOs) {
        this.orderDetailDTOs = orderDetailDTOs;
    }
}
