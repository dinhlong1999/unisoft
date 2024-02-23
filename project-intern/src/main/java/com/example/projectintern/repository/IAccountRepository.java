package com.example.projectintern.repository;

import com.example.projectintern.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface  IAccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUsername(String username);

    @Modifying
    @Query(value = "INSERT INTO `account`(" +
            "             `password`," +
            "              username," +
            "              role_id)\n" +
            "       VALUES (" +
            "             :password," +
            "             :username," +
            "             :roleId" +
            "              )",nativeQuery = true)
    void saveAccount(@Param("password") String password,
                     @Param("username") String username,
                     @Param("roleId") int roleId);

    @Query(value = "SELECT " +
            "            `account`.id  " +
            "       FROM " +
            "            `account` " +
            "       WHERE " +
            "            `account`.username = :username",nativeQuery = true)
    int getAccountByUsername(@Param("username") String username);
}
