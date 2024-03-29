package com.example.springmvc.model;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "bit(1) default 0",nullable = false)
    private boolean flag;

    @Column(columnDefinition = "int default 1",nullable = false)
    private int version;
    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    public Account() {
    }

    public Account(int id, String username, String password, boolean flag, Role role, int version) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.role = role;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}