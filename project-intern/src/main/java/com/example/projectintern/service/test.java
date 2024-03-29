package com.example.projectintern.service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test {
    public static void exercise1(int[] a, int[] b) {
        int[] arr = new int[a.length];
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            boolean flag = true;

            for (int k = 0; k < index; k++) {
                if (a[i] == arr[k]){
                    flag = false;
                    break;
                }
            }


            for (int j = 0; j < b.length ; j++) {
                if (a[i] == b[j]){
                    if (flag){
                        arr[index++] = a[i];
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < index; i++) {
            System.out.println(arr[i]);
        }

    }

    public static void exercise2(String str) {
        int result = 0;
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (arr[i] == '.') {
                result += 1;
            }
        }
        System.out.println(result);
    }

    public static void exercise3(int count) {
        count++;
        if (count <= 5) {
            System.out.println("Steap at" + count);
            exercise3(count);
        }
    }

    public static int exercise33(List<Employee> employeeList, int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                return employee.getPointImportant() + dequy(employee.getSubordinates(),employeeList);
            }
        }
        return 0;
    }

    public static int dequy(List<Integer> subordinate, List<Employee> employeeList) {
        int result = 0;
        for (Integer integer: subordinate) {
            result += exercise33(employeeList,integer);
        }
        return result;
    }
    public static int thuattoan(List<List<Integer>> arrayList){
        int result = 0;
        int duongcheochinh = 0;
        int duongcheophu = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            List<Integer> list = arrayList.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (i == j){
                    duongcheochinh += list.get(j);
                }
                if (i + j == list.size() -1){
                    duongcheophu +=list.get(j);
                }
            }
             result = Math.abs(duongcheochinh - duongcheophu);
        }
        return  result;
    }
    public static void thuattoan2(List<Integer> arr) {
        double positive = 0.0;
        double negative = 0.0;
        double zero = 0;
        int countNegative = 0;
        int countPositive = 0;
        int countZero = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > 0){
                countPositive+=1;
            }else if ( arr.get(i) < 0){
                countNegative+=1;
            }else {
                countZero+=1;
            }
        }
        positive = (double) countPositive / arr.size();
        negative = (double) countNegative / arr.size();
        zero = (double) countZero / arr.size();
        System.out.println(positive);
        System.out.println(negative);
        System.out.println(zero);

    }
    public static void print(int a){
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
               if (i < (a - j - 1)){
                   System.out.print(" ");
               }else {
                   System.out.print("#");
               }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[] a = {1,3,1,5};
        int[] b = {1,1,1,5,3,5,5,5,5};
        exercise1(a,b);
//        List<List<Integer>> arrayList = new ArrayList<>();
//        List<Integer> array = new ArrayList<>(Arrays.asList(1,2,3,1));
//        List<Integer> array1 = new ArrayList<>(Arrays.asList(1,2,3,2));
//        List<Integer> array2 = new ArrayList<>(Arrays.asList(9,2,3,3));
//        List<Integer> array3 = new ArrayList<>(Arrays.asList(9,2,3,4));
//        arrayList.add(array);
//        arrayList.add(array1);
//        arrayList.add(array2);
//        arrayList.add(array3);
//        thuattoan(arrayList);
//    List<Integer> arrayList = new ArrayList<>(Arrays.asList(-4,3 -9,0,4,1));
//    thuattoan2(arrayList);
//    print(4);
//        String str ="Việt nam vô địch. Việt Nam vô địch. abc áđâsd. áđâsd á.";
//        exercise2(str);
//        exercise3(1);
//        Employee employee1 = new Employee(1, 5, new ArrayList<>(Arrays.asList(2, 3)));
//        Employee employee2 = new Employee(2, 3, new ArrayList<>(Arrays.asList(4, 5)));
//        Employee employee3 = new Employee(3, 1, new ArrayList<>(Collections.emptyList()));
//        Employee employee4 = new Employee(4, 1, new ArrayList<>(Collections.emptyList()));
//        Employee employee5 = new Employee(5, 10, new ArrayList<>(Collections.emptyList()));
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//        employeeList.add(employee3);
//        employeeList.add(employee4);
//        employeeList.add(employee5);
//        System.out.println(exercise33(employeeList,1));
    }

}
