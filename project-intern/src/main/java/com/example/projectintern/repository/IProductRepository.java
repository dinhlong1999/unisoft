package com.example.projectintern.repository;

import com.example.projectintern.dto.IProductDetail;
import com.example.projectintern.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT " +
            "          id, " +
            "          code_product as codeProduct, " +
            "          name_product as nameProduct, " +
            "          flag, " +
            "          price_buy    as priceBuy," +
            "          price_sell   as priceSell, " +
            "          version " +
            "       FROM" +
            "          product " +
            "       WHERE" +
            "          code_product LIKE :code_Product " +
            "       AND" +
            "          name_product LIKE :name_Product " +
            "       ORDER BY" +
            "          name_product",nativeQuery = true)
    Page<IProductDetail> getAllProduct(Pageable pageable,
                                       @Param("code_Product") String codeProduct,
                                       @Param("name_Product") String nameProduct);

    @Query(value = "SELECT " +
            "           count(*)" +
            "       FROM" +
            "           product " +
            "       WHERE " +
            "           code_product LIKE :codeProduct " +
            "       AND " +
            "           name_product LIKE :nameProduct ",nativeQuery = true)
    int getTotalRecordProduct(@Param("codeProduct") String codeProduct,
                              @Param("nameProduct") String nameProduct);

    @Query(value = " SELECT " +
            "           id, " +
            "           code_product    AS codeProduct, " +
            "           name_product    AS nameProduct, " +
            "           flag, price_buy AS priceBuy, " +
            "           price_sell      AS priceSell," +
            "           version \n" +
            "       FROM " +
            "           product " +
            "       WHERE " +
            "           code_product LIKE :codeProduct " +
            "       AND " +
            "           name_product LIKE :nameProduct " +
            "       ORDER BY " +
            "           name_product " +
            "       LIMIT " +
            "           :limitNumber OFFSET :pageNumber",nativeQuery = true)
    List<IProductDetail> getAllProductByNameProductAndCodeProduct(@Param("codeProduct") String codeProduct,
                                                                  @Param("nameProduct") String nameProduct,
                                                                  @Param("limitNumber") int limitNumber,
                                                                  @Param("pageNumber") int pageNumber);


    Product findProductByCodeProduct(String codeProduct);
    Product findProductByNameProduct(String nameProduct);

    @Modifying
    @Query(value = "INSERT INTO product(" +
            "               code_product," +
            "               name_product," +
            "               price_buy," +
            "               price_sell)\n" +
            "       VALUES(" +
            "               :codeProduct, " +
            "               :nameProduct, " +
            "               :priceBuy, " +
            "               :priceSell" +
            "              )",nativeQuery = true)
    void saveProduct(@Param("codeProduct") String codeProduct,
                     @Param("nameProduct") String nameProduct,
                     @Param("priceBuy") double priceBuy,
                     @Param("priceSell") double priceSell);

    @Modifying
    @Query(value = "UPDATE" +
            "           product " +
            "       SET " +
            "           code_product = :codeProduct, " +
            "           name_product = :nameProduct, " +
            "           price_buy    = :priceBuy," +
            "           price_sell   = :priceSell," +
            "           version      = :version + 1 " +
            "       WHERE " +
            "           id = :productId " +
            "       AND " +
            "           version = :version",nativeQuery = true)
    int updateProduct(@Param("codeProduct") String codeProduct,
                      @Param("nameProduct") String nameProduct,
                      @Param("priceBuy") double priceBuy,
                      @Param("priceSell") double priceSell,
                      @Param("productId") int productId,
                      @Param("version") int version );




}
