package com.zlw.crowdsourcing.utils;

import com.zlw.crowdsourcing.pojo.Worker;

import java.util.*;

public class TestList {
    public static void main(String[] args) {
        HashMap<String, Double> map = new HashMap<>();
        map.put("2",20.520);
        map.put("1",50.2044);
        map.put("3",10.254);
        List<Map.Entry<String, Double>> list_Data = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
//        System.out.println(list_Data);
        for (int i = 0; i < 2 ; i++){
            System.out.println(list_Data.get(i).getKey());
        }
    }
}
