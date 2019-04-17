package com.example.springboot;

import java.util.ArrayList;
import java.util.List;
/*
* 随便挪动一个值取最大
* */
public class arithmetictest {
    public static void main(String[] args) {
        String a = "666888";
        String[] arr = a.split("");
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            StringBuffer buffer = new StringBuffer(a);
            String testm = arr[i] + buffer.replace(i, i + 1, "").toString();
            list.add(Integer.parseInt(testm));
        }
        int max = list.get(0);
        for (int j = 0; j < list.size(); j++) {
            if (max < list.get(j)) {
                max = list.get(j);
            }
        }
        System.out.println(max);
        StringBuilder sb = new StringBuilder("18698587234");
        System.out.println(sb.replace(0, 1, ""));
    }
}
