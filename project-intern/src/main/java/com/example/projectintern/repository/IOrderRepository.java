package com.example.projectintern.repository;

import com.example.projectintern.dto.orderDetail.ICustomerNoOrderDTO;
import com.example.projectintern.dto.orderDetail.IOrderDetailDTO;
import com.example.projectintern.dto.orderDetail.IProductAnalystDTO;
import com.example.projectintern.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT " +
            "             o.date_start      AS dateStart," +
            "             p.price_buy       AS priceBuy," +
            "             p.code_product    AS codeProduct," +
            "             o.quantity_book   AS quantityBook," +
            "             c.`name`          AS customerName," +
            "             c.phone_number    AS customerPhoneNumber," +
            "             c.address         AS customerAddress," +
            "             s.`name`          AS statusName," +
            "             a.username        AS accountName," +
            "             e.`name`          AS employeeName," +
            "             o.date_allocation AS dateAllocation\n" +
            "        FROM" +
            "             order_detail o\n" +
            "        JOIN " +
            "             customer c ON c.id = o.customer_id\n " +
            "        JOIN " +
            "             employee e ON e.id = o.employee_id \n" +
            "        JOIN " +
            "            `account` a ON a.id = e.account_id\n" +
            "        JOIN   " +
            "             product p  ON p.id = o.product_id\n" +
            "        JOIN   " +
            "            `status` s ON s.id = o.status_id\n" +
            "        WHERE \n" +
            "           CASE \n" +
            "              WHEN :codeProduct IS NULL OR :codeProduct = '' THEN p.code_product like 'PR-%'\n" +
            "              ELSE p.code_product like CONCAT('%', :codeProduct, '%')\n " +
            "           END" +
            "        AND" +
            "           a.username LIKE :accountName \n" +
            "        AND" +
            "           e.`name`   LIKE :employeeName \n" +
            "        AND " +
            "           c.`name`   LIKE :customerName \n" +
            "        AND" +
            "           CASE \n" +
            "              WHEN :customerPhoneNumber IS NULL OR :customerPhoneNumber = '' THEN c.phone_number like '0%'\n" +
            "              ELSE c.phone_number like CONCAT('%', :customerPhoneNumber, '%')\n" +
            "           END" +
            "        AND " +
            "           date_start between :dateStart AND :dateEnd " +
            "        AND " +
            "           (:isAdmin = true or o.employee_id = :employeeId) " +
            "        AND" +
            "           o.status_id IN  (:statusId)" +
            "        ORDER BY " +
            "                o.date_start desc " +
            "        LIMIT " +
            "             :limit OFFSET :page", nativeQuery = true)
    List<IOrderDetailDTO> getOrderDetailByAdmin(@Param("accountName") String accountName,
                                                @Param("employeeName") String employeeName,
                                                @Param("codeProduct") String codeProduct,
                                                @Param("customerName") String customerName,
                                                @Param("customerPhoneNumber") String customerPhoneNumber,
                                                @Param("dateStart") LocalDate dateStart,
                                                @Param("dateEnd") LocalDate dateEnd,
                                                @Param("isAdmin") boolean isAdmin,
                                                @Param("employeeId") int employeeId,
                                                @Param("limit") int limit,
                                                @Param("page") int page,
                                                @Param("statusId") List<Integer> statusList);



    @Query(value = "SELECT " +
            "              max(date_start) " +
            "       FROM " +
            "           order_detail", nativeQuery = true)
    LocalDateTime getMaxDateStart();

    @Query(value = "SELECT " +
            "              min(date_start) " +
            "       FROM " +
            "              order_detail", nativeQuery = true)
    LocalDateTime getMinDateStart();

    List<OrderDetail> getOrderDetailByProduct_IdOrderByDateStart(int id);

    @Modifying
    @Query(value = "UPDATE " +
            "             order_detail " +
            "       SET " +
            "           date_start    = :dateStart, " +
            "           quantity_book = :quantityBook," +
            "           customer_id   = :customerId," +
            "           employee_id   = :employeeId," +
            "           product_id    = :productId," +
            "           price         = :price " +
            "       WHERE " +
            "           id = :id " +
            "       AND    " +
            "           status_id = 1;", nativeQuery = true)
    int updateOrder(@Param("dateStart") LocalDate dateStart,
                    @Param("quantityBook") int quantityBook,
                    @Param("customerId") int customerId,
                    @Param("employeeId") int employeeId,
                    @Param("productId") int productId,
                    @Param("price") double price,
                    @Param("id") int id);

    @Modifying
    @Query(value = "INSERT INTO order_detail(" +
            "                   date_start," +
            "                   quantity_book," +
            "                   customer_id," +
            "                   employee_id," +
            "                   product_id," +
            "                   status_id," +
            "                   price)" +
            "       VALUES(" +
            "                   :dateStart," +
            "                   :quantityBook," +
            "                   :customerId," +
            "                   :employeeId," +
            "                   :productId," +
            "                   :statusId," +
            "                   :price)",nativeQuery = true)
    int saveOrder(@Param("dateStart") LocalDate dateStart,
                  @Param("quantityBook") int quantityBook,
                  @Param("customerId") int customerId,
                  @Param("employeeId") int employeeId,
                  @Param("productId") int productId,
                  @Param("statusId") int statusId,
                  @Param("price") double price);


    @Procedure(name = "import_product")
    @Modifying
    void import_product(@Param("id_product") int productId,
                        @Param("quantity")   int quantity);

    @Query(value = "SELECT " +
            "           c.id           as customerId," +
            "           c.name         as customerName," +
            "           c.phone_number as customerPhoneNumber," +
            "           c.address      as customerAddress " +
            "       FROM" +
            "           customer c " +
            "       LEFT JOIN " +
            "           (SELECT \n" +
            "                c.id AS customer_id," +
            "                c.`name`, " +
            "                o.id\n" +
            "            FROM   \n" +
            "                customer c \n" +
            "            LEFT JOIN \n" +
            "                order_detail o ON o.customer_id = c.id " +
            "            WHERE \n" +
            "                o.date_start BETWEEN :dateStart AND :dateEnd " +
            "           ) AS temp ON temp.customer_id = c.id " +
            "       WHERE " +
            "               customer_id IS NULL" +
            "       LIMIT " +
            "           :limit OFFSET :page ",nativeQuery = true)
    List<ICustomerNoOrderDTO> getListCustomerNoOrder(@Param("dateStart") LocalDate dateStart,
                                                     @Param("dateEnd")   LocalDate dateEnd,
                                                     @Param("limit") int limit,
                                                     @Param("page")  int page);

    @Query(value = "SELECT " +
            "             p.id                 AS productId, " +
            "             p.name_product       AS nameProduct, " +
            "             p.code_product       AS codeProduct, \n" +
            "             sum(o.quantity_book) AS quantity " +
            "        FROM " +
            "             product p\n" +
            "        JOIN " +
            "             order_detail o ON o.product_id = p.id\n" +
            "        WHERE " +
            "             o.date_start BETWEEN :dateStart AND :dateEnd " +
            "        GROUP BY " +
            "             p.code_product, p.id\n" +
            "        ORDER BY " +
            "             quantity DESC" +
            "        LIMIT" +
            "             :limit OFFSET :page",nativeQuery = true)

    List<IProductAnalystDTO> getListProductBestSeller(@Param("dateStart") LocalDate dateStart,
                                                      @Param("dateEnd") LocalDate dateEnd,
                                                      @Param("limit") int limit,
                                                      @Param("page") int page);

    @Query(value = "SELECT COUNT(*) AS totalRecord FROM \n" +
            "                                       (SELECT " +
            "                                             p.id                 AS productId, " +
            "                                             p.name_product       AS nameProduct, " +
            "                                             p.code_product       AS codeProduct, \n" +
            "                                             SUM(o.quantity_book) AS quantity\n" +
            "                                        FROM " +
            "                                             product p " +
            "                                        JOIN " +
            "                                             order_detail o ON o.product_id = p.id\n" +
            "                                        WHERE " +
            "                                             o.date_start BETWEEN :dateStart AND :dateEnd " +
            "                                        GROUP BY \n" +
            "                                             p.code_product, p.id\n" +
            "                                        ) AS product_best_seller",nativeQuery = true)
    double getTotalRecordByProductBestSeller(@Param("dateStart") LocalDate dateStart,
                                          @Param("dateEnd") LocalDate dateEnd);


    @Query(value = "SELECT " +
            "            p.id            AS productId, " +
            "            p.name_product  AS nameProduct, " +
            "            p.code_product  AS codeProduct " +
            "       FROM " +
            "            product p\n" +
            "       LEFT JOIN" +
            "            (SELECT  " +
            "                  p.id AS product_id  " +
            "             FROM " +
            "                  product p\n" +
            "             JOIN " +
            "                  order_detail o ON o.product_id = p.id\n" +
            "             WHERE " +
            "                  o.date_start BETWEEN :dateStart AND :dateEnd" +
            ") AS tableTemp ON tableTemp.product_id =p.id " +
            "       WHERE" +
            "            product_id IS NULL" +
            "       LIMIT" +
            "           :limit OFFSET :page",nativeQuery = true)
    List<IProductAnalystDTO> getListProductNoBought(@Param("dateStart") LocalDate dateStart,
                                                    @Param("dateEnd") LocalDate dateEnd,
                                                    @Param("limit") int limit,
                                                    @Param("page") int page);

    @Query(value = "SELECT " +
            "           COUNT(*) AS total_record" +
            "       FROM " +
            "            (SELECT " +
            "                   p.id           AS productId, " +
            "                   p.name_product AS nameProduct, " +
            "                   p.code_product AS codeProduct " +
            "             FROM " +
            "                   product p " +
            "             WHERE " +
            "                   p.id NOT IN" +
            "                           (SELECT " +
            "                                  p.id" +
            "                            FROM " +
            "                                  product p" +
            "                            JOIN " +
            "                                  order_detail o ON o.product_id = p.id " +
            "                            WHERE " +
            "                                  o.date_start BETWEEN :dateStart AND :dateEnd " +
            "                             ) " +
            "             ) AS product_no_seller_view",nativeQuery = true)
    double getTotalRecordByProductNoSeller(@Param("dateStart") LocalDate dateStart,
                                        @Param("dateEnd") LocalDate dateEnd);

    @Query(value = "SELECT " +
            "count(*) as record " +
            "FROM " +
            "       order_detail o " +
            "JOIN " +
            "       customer c  ON c.id = o.customer_id  " +
            "JOIN " +
            "       employee e  ON e.id = o.employee_id " +
            "JOIN " +
            "       `account` a ON  a.id = e.account_id " +
            "JOIN " +
            "       product p   ON p.id = o.product_id " +
            "JOIN " +
            "       `status` s  ON s.id = o.status_id " +
            "WHERE " +
            "   CASE " +
            "   WHEN :codeProduct IS NULL OR :codeProduct = '' THEN p.code_product LIKE 'PR-%' " +
            "         ELSE p.code_product like CONCAT('%',:codeProduct, '%')" +
            "   END" +
            " AND " +
            "     a.username LIKE :accountName " +
            " AND " +
            "     e.`name` LIKE :employeeName " +
            " AND " +
            "     c.`name` LIKE :customerName " +
            " AND " +
            "   CASE " +
            "       WHEN :customerPhoneNumber IS NULL OR :customerPhoneNumber = '' THEN c.phone_number LIKE '0%' " +
            "       ELSE c.phone_number LIKE CONCAT('%', :customerPhoneNumber, '%') " +
            "   END " +
            "  AND" +
            "         date_start BETWEEN :dateStart AND :dateEnd  " +
            "  AND " +
            "          (:isAdmin = true OR o.employee_id = :employeeId) \n" +
            "  ORDER BY " +
            "    o.date_start desc",nativeQuery = true)
    int countRecordByOrderRequest(@Param("accountName") String accountName,
                                  @Param("employeeName") String employeeName,
                                  @Param("codeProduct") String codeProduct,
                                  @Param("customerName") String customerName,
                                  @Param("customerPhoneNumber") String customerPhoneNumber,
                                  @Param("dateStart") LocalDate dateStart,
                                  @Param("dateEnd") LocalDate dateEnd,
                                  @Param("isAdmin") boolean isAdmin,
                                  @Param("employeeId") int employeeId);

}
