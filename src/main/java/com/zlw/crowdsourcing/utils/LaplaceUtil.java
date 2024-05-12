package com.zlw.crowdsourcing.utils;

import com.alibaba.fastjson.JSONObject;

public class LaplaceUtil {
    final static int earth_radius = 6378137;

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("latitude",39.989677);
        json.put("longitude",116.480656);
        JSONObject noisePos = addNoise(0.04, json);
        System.out.println(noisePos);
    }

    public static JSONObject addNoise(double epsilon, JSONObject pos){
        return addPolarNoise(epsilon,pos);
    }

    private static JSONObject addPolarNoise(double epsilon, JSONObject pos) {
        double theta = Math.random() * Math.PI * 2;
        double z = Math.random();
        double r = inverseCumulativeGamma(epsilon,z);
        return addVectorToPos(pos,r,theta);
    }

    private static double inverseCumulativeGamma(double epsilon, double z) {
        double x = (z-1)/Math.E;
        return - (LambertW(x) + 1) / epsilon;
    }

    private static double LambertW(double x) {
        double min_diff = 1e-10;
        if (x == -1/Math.E){
            return -1;
        }else if(x < 0 && x > -1 / Math.E){
            double q = Math.log(-x);
            double p = 1;
            while (Math.abs(p-q) > min_diff){
                p=(q*q+x/Math.exp(q))/(q+1);
                q=(p*p+x/Math.exp(p))/(p+1);
            }
            return (Math.round(1000000*q)/1000000);
        }else if (x == 0){
            return 0;
        }else{
            return 0;
        }
    }

    private static JSONObject addVectorToPos(JSONObject pos, double distance, double angle) {
        double ang_distance = distance / earth_radius;
        double lat1 = rad_of_deg((Double) pos.get("latitude"));
        double lon1 = rad_of_deg((Double) pos.get("longitude"));

        double lat2 =	Math.asin(Math.sin(lat1) * Math.cos(ang_distance) +
                Math.cos(lat1) * Math.sin(ang_distance) * Math.cos(angle)
        );
        double lon2 =	lon1 +
                Math.atan2(
                        Math.sin(angle) * Math.sin(ang_distance) * Math.cos(lat1),
                        Math.cos(ang_distance) - Math.sin(lat1) * Math.sin(lat2)
                );
        lon2 = (lon2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI;		// normalise to -180..+180

        JSONObject json = new JSONObject();
        json.put("latitude",deg_of_rad(lat2));
        json.put("longitude",deg_of_rad(lon2));
        return json;
    }

    private static double deg_of_rad(double ang) {
        return ang * 180 / Math.PI;
    }

    private static double rad_of_deg(double ang) {
        return ang * Math.PI / 180;
    }
}
