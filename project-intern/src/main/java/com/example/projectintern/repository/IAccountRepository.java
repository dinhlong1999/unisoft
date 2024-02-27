package com.example.projectintern.repository;

import com.example.projectintern.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;


public interface  IAccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUsername(String username);

    @Query(value = "select \n" +
            "           username, \n" +
            "           password,\n" +
            "           `role`.`name` as roleName\n" +
            "       from \n" +
            "\t         `account`\n" +
            "       join \n" +
            "\t         `role` on `role`.id = `account`.role_id\n" +
            "       where \n" +
            "\t          username = :username " +
            "       and\t\n" +
            "\t           password = :password",nativeQuery = true)
    Map<String,Object> getAccountByUsernameAndPassword(@Param("username") String username,
                                                       @Param("password") String password);

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
