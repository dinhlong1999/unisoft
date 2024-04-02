package com.example.springmvc.model;

public class Account {

    private int id;
    private String username;

    private String password;

    private boolean flag;


    private int version;

    private int roleId;

    public Account() {
    }

    public Account(int id, String username, String password, boolean flag, int roleId, int version) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.roleId = roleId;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}