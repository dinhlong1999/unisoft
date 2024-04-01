package com.example.springmvc.repository;

import com.example.springmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select " +
            "          id, code_product, flag, inventory, name_product, price_buy,price_sell " +
            "       from " +
            "           product p " +
            "       where" +
            "           p.code_product like %:codeProduct%" +
            "       and" +
            "           p.name_product like %:nameProduct%" +
            "       and" +
            "           flag = 0" +
            "       limit" +
            "            :limit offset :page", nativeQuery = true)
    List<Map<String, Object>> getListProduct(@Param("codeProduct") String codeProduct,
                                             @Param("nameProduct") String nameProduct,
                                             @Param("limit") int limit,
                                             @Param("page") int page);

    @Query(value = "select " +
            "           count(*) " +
            "       from " +
            "           product p " +
            "       where" +
            "           p.code_product like %:codeProduct%" +
            "       and" +
            "           p.name_product like %:nameProduct%" +
            "       and" +
            "           flag = 0", nativeQuery = true)
    int totalCountGetListProduct(@Param("codeProduct") String codeProduct,
                                 @Param("nameProduct") String nameProduct
                                 );
    @Modifying
    @Query(value = "update product set flag = 1 where id = :id",nativeQuery = true)
    int deleteProduct(@Param("id") int id);
}
