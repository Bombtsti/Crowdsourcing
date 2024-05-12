package com.zlw.crowdsourcing.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationConvertUtil {
    //地址转经纬度
    public static String getLnglat(String address) {
        String key = "145ef2b3b60c7ddc4ee3e1e70db379c7";
        String geturl = "http://restapi.amap.com/v3/geocode/geo?key="+key+"&address="+address;
        String location = "";
        try {
            URL url = new URL(geturl);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            JSONObject a = JSON.parseObject(sb.toString());
            //判断输入的位置点是否存在
            //System.out.println(sb.toString());
            if(a.getJSONArray("geocodes").size()>0)
                location=a.getJSONArray("geocodes").getJSONObject(0).get("location").toString();
            //System.out.println(location);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("失败!");
        }
        return location;
    }
    //经纬度转地址
    public static String getLocationDesc(String loc){
        String key = "145ef2b3b60c7ddc4ee3e1e70db379c7";
        String geturl = "https://restapi.amap.com/v3/geocode/regeo?key="+key+"&location="+loc;
        String desc = "";
        try {
            URL url = new URL(geturl);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            JSONObject a = JSON.parseObject(sb.toString());
            //判断输入的位置点是否存在
            //System.out.println(sb.toString());
            if(a.getJSONObject("regeocode").size()>0){
                desc=a.getJSONObject("regeocode").get("formatted_address").toString();
            }
            //System.out.println(desc);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("失败!");
        }
        return desc;
    }
}
