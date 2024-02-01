package com.example.projectintern.repository;

import com.example.projectintern.dto.orderDetail.IOrderDetailDTO;
import com.example.projectintern.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "    SELECT \n" +
            "    o.date_start AS dateStart,\n" +
            "    p.price_buy AS priceBuy,\n" +
            "    p.code_product AS codeProduct,\n" +
            "    o.quantity_book AS quantityBook,\n" +
            "    c.`name` AS customerName,\n" +
            "    c.phone_number AS customerPhoneNumber,\n" +
            "    c.address AS customerAddress,\n" +
            "    s.`name` AS statusName,\n" +
            "    a.username AS accountName,\n" +
            "    e.`name` AS employeeName,\n" +
            "    o.date_allocation AS dateAllocation\n" +
            "FROM\n" +
            "    order_detail o\n" +
            "JOIN customer c ON c.id = o.customer_id\n" +
            "JOIN employee e ON e.id = o.employee_id \n" +
            "JOIN `account` a ON a.id = e.account_id\n" +
            "JOIN product p ON p.id = o.product_id\n" +
            "JOIN `status` s ON s.id = o.status_id\n" +
            "WHERE \n" +
            "    a.username LIKE :accountName \n" +
            "    AND e.`name` LIKE :employeeName \n" +
            "    AND p.code_product LIKE :codeProduct\n" +
            "    AND c.`name` LIKE :customerName \n" +
            "    AND c.phone_number LIKE :customerPhoneNumber \n" +
            "    and date_start between :dateStart and :dateEnd order by o.date_start desc" ,nativeQuery = true)
    List<IOrderDetailDTO> getOrderDetailByAdmin(@Param("accountName") String accountName,
                                                @Param("employeeName") String employeeName,
                                                @Param("codeProduct") String codeProduct,
                                                @Param("customerName")String customerName,
                                                @Param("customerPhoneNumber") String customerPhoneNumber,
                                                @Param("dateStart") LocalDate dateStart,
                                                @Param("dateEnd") LocalDate dateEnd);

    @Query(value = "    SELECT \n" +
            "    o.date_start AS dateStart,\n" +
            "    p.price_buy AS priceBuy,\n" +
            "    p.code_product AS codeProduct,\n" +
            "    o.quantity_book AS quantityBook,\n" +
            "    c.`name` AS customerName,\n" +
            "    c.phone_number AS customerPhoneNumber,\n" +
            "    c.address AS customerAddress,\n" +
            "    s.`name` AS statusName,\n" +
            "    o.date_allocation AS dateAllocation\n" +
            "FROM\n" +
            "    order_detail o\n" +
            "JOIN customer c ON c.id = o.customer_id\n" +
            "JOIN employee e ON e.id = o.employee_id and o.employee_id = :idEmployee " +
            "JOIN `account` a ON a.id = e.account_id\n" +
            "JOIN product p ON p.id = o.product_id\n" +
            "JOIN `status` s ON s.id = o.status_id\n" +
            "WHERE \n" +
            "    p.code_product LIKE :codeProduct\n" +
            "    AND c.`name` LIKE :customerName \n" +
            "    AND c.phone_number LIKE :customerPhoneNumber \n" +
            "    and date_start between :dateStart and :dateEnd order by o.date_start desc",nativeQuery = true)
    List<IOrderDetailDTO> getOrderDetailByUser(@Param("idEmployee") int idEmployee,
                                                @Param("codeProduct") String codeProduct,
                                                @Param("customerName")String customerName,
                                                @Param("customerPhoneNumber") String customerPhoneNumber,
                                                @Param("dateStart") LocalDate dateStart,
                                                @Param("dateEnd") LocalDate dateEnd);
    @Query(value = "select max(date_start) from order_detail",nativeQuery = true)
    LocalDateTime getMaxDateStart();

    @Query(value = "select min(date_start) from order_detail",nativeQuery = true)
    LocalDateTime getMinDateStart();

    List<OrderDetail> getOrderDetailByProduct_IdOrderByDateStart(int id);

}
