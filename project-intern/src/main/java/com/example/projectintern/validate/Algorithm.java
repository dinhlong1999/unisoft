package com.example.projectintern.validate;

import java.util.Map;
import java.util.TreeMap;

public class Algorithm {
    public static int calculate(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 1; j <= arr[i]; j++) {
                if (arr[i] % j == 0) {
                    count++;
                }
            }
            if (count == 2) {
                result += arr[i];
            }
        }
        return result;
    }

    public static void exercise(String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], 1);
            } else {
                map.put(chars[i], map.get(chars[i]) + 1);
            }
        }
        for (Character character : map.keySet()) {
            System.out.println(character + "-" + map.get(character));
        }
    }

    public static String exercise1(String str) {
        char[] chars = str.toCharArray();
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] > chars[j]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
            result += chars[i];
        }
        return result;
    }

    public static Map<Integer, Integer> exercise2(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    if (!map.containsKey(arr1[i])) {
                        map.put(arr1[i], 1);
                    } else {
                        map.put(arr1[i], map.get(arr1[i]) + 1);
                    }
                }
            }
        }
        return map;
    }


    public static void exercise3(String str) {
        char[] chars = str.toCharArray();
        String result = "";
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], 1);
            } else {
                map.put(chars[i], map.get(chars[i]) + 1);
            }
        }
        for (Character character : map.keySet()) {
            System.out.println(character + "_" + map.get(character));
            result += character;
        }
        System.out.println("Chuỗi trước khi bỏ : " + str);
        System.out.println("Chuỗi đã bỏ: " + result);


    }

    public static void exercise4(int[] arr, int number) {
        int temp = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < number && temp == 0) {
                map.put(i, arr[i]);
                temp = number - arr[i];
            }
            if (temp == arr[i]) {
                map.put(i, arr[i]);
            }
        }
        for (Integer result : map.keySet()) {
            System.out.println(result + "-" + map.get(result));
        }
    }

    public static void exercise5(String str) {
        char[] chars = str.toCharArray();
        boolean result = true;
        for (int i = 0; i < str.length() / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                result = false;
                break;
            }
        }
        if (result){
            System.out.println("ok");
        }else {
            System.out.println("Bipppp");
        }
    }


    public static void main(String[] args) {
//        int[] arr = {1,1,2,1,1};
//        int result = calculate(arr);
//        System.out.println(result);
//        exercise("aaabbccd");
//        System.out.println(exercise1("adabvf"));
//        String str = "adabvf";
//        for (int i = 0; i < str.length(); i++) {
//            System.out.println(+str.charAt(i));
//        }
//        int[] arr1 = {1, 2, 3, 4, 5, 6};
//        int[] arr2 = {1,3,5,7,2};
//        Map<Integer, Integer> map = exercise2(arr1,arr2);
//        for (Integer integer : map.keySet()) {
//            System.out.println(integer);
//        }
//        int[] array = {1, 3, 5, 7};
//        exercise4(array, 8);
        exercise5("abcca");
    }


}
