package com.example.projectintern.service;

import java.util.List;

public class Employee {
    private int id;
    private int pointImportant;
    private List<Integer> subordinates;

    public Employee() {
    }

    public Employee(int id, int pointImportant, List<Integer> subordinates) {
        this.id = id;
        this.pointImportant = pointImportant;
        this.subordinates = subordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointImportant() {
        return pointImportant;
    }

    public void setPointImportant(int pointImportant) {
        this.pointImportant = pointImportant;
    }

    public List<Integer> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Integer> subordinates) {
        this.subordinates = subordinates;
    }
}
