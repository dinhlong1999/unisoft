package com.example.projectintern.repository;

import com.example.projectintern.dto.IProductDetail;
import com.example.projectintern.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select id, code_product as codeProduct, name_product as nameProduct, flag, price_buy as priceBuy, price_sell as priceSell, version " +
            "from product where code_product like :code_Product and name_product like :name_Product order by name_product",nativeQuery = true)
    Page<IProductDetail> getAllProduct(Pageable pageable,
                                       @Param("code_Product") String codeProduct,
                                       @Param("name_Product") String nameProduct);

    @Query(value = "select count(*) from product where code_product like :codeProduct and name_product like :nameProduct ",nativeQuery = true)
    int getTotalRecordProduct(@Param("codeProduct") String codeProduct,
                              @Param("nameProduct") String nameProduct);

    @Query(value = " select id, code_product as codeProduct, name_product as nameProduct, flag, price_buy as priceBuy, price_sell as priceSell, version \n" +
            "            from product where code_product like :codeProduct and name_product like :nameProduct order by name_product limit :limitNumber offset :pageNumber",nativeQuery = true)
    List<IProductDetail> getAllProductByNameProductAndCodeProduct(@Param("codeProduct") String codeProduct,
                                                                  @Param("nameProduct") String nameProduct,
                                                                  @Param("limitNumber") int limitNumber,
                                                                  @Param("pageNumber") int pageNumber);


    Product findProductByCodeProduct(String codeProduct);
    Product findProductByNameProduct(String nameProduct);


}
