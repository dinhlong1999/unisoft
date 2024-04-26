package com.example.springmvc.common;

import java.util.Map;
import java.util.TreeMap;

public class Paging {
    public static Map<String,Object> handlePaging(int page, int totalPage){
        Map<String,Object> result = new TreeMap<>();
        int maxVisitablePages = 10; // Số trang tối đa hiển thị
        int adjacentPages = 2; // số trang bên cạnh trang hiện tại
        int startPage;
        int endPage;
        boolean showStartEllipsis = false; // Dấu ... đầu
        boolean showEndEllipsis = false; // Dấu ... cuối
        if (totalPage <= maxVisitablePages) {
            startPage = 1;
            endPage = totalPage;
        } else {
            if (page <= maxVisitablePages - adjacentPages) {
                startPage = 1;
                endPage = maxVisitablePages;
                showEndEllipsis = true;
            } else if (page >= totalPage - adjacentPages) {
                startPage = totalPage - maxVisitablePages + 1;
                endPage = totalPage;
                showStartEllipsis = true;
            } else {
                startPage = page - adjacentPages;
                endPage = page + adjacentPages;
                showStartEllipsis = true;
                showEndEllipsis = true;
            }
        }
        result.put("startPage",startPage);
        result.put("endPage",endPage);
        result.put("showStartEllipsis",showStartEllipsis);
        result.put("showEndEllipsis",showEndEllipsis);
        return  result;
    }
}
