package com.example.projectintern.dto.orderDetail;

import jdk.vm.ci.meta.Local;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public interface IOrderDetailDTO {
    String getDateStart();
    double getPriceBuy();

    String getCodeProduct();
    int getQuantityBook();
    String getCustomerName();
    String getCustomerPhoneNumber();
    String getCustomerAddress();
    String getStatusName();
    String getAccountName();
    String getEmployeeName();
    String getDateAllocation();
}
