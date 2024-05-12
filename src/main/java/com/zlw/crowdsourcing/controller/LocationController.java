package com.zlw.crowdsourcing.controller;


import com.alibaba.fastjson.JSONObject;
import com.zlw.crowdsourcing.mapper.LocationMapper;
import com.zlw.crowdsourcing.utils.LaplaceUtil;
import com.zlw.crowdsourcing.utils.LocationConvertUtil;
import com.zlw.crowdsourcing.vo.ResultVo;
import com.zlw.crowdsourcing.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Controller
public class LocationController {

    @Autowired
    private LocationMapper locationMapper;

    //更改工作者的位置
    @RequestMapping("/loc/updateLoc/{loc}")
    @ResponseBody
    public ResultVo updateLoc(@PathVariable("loc") String loc, HttpSession session){
        String[] str = loc.split("\\,");
        String longitude = str[0];
        String latitude = str[1];

        //添加噪声
        JSONObject pos = new JSONObject();
        pos.put("latitude", Double.parseDouble(latitude));
        pos.put("longitude",Double.parseDouble(longitude));
        JSONObject noisePos = LaplaceUtil.addNoise(0.04, pos);
        String noiseLong = String.valueOf(noisePos.get("longitude"));
        String noiseLat = String.valueOf(noisePos.get("latitude"));

        String noiseLoc = noiseLong+","+noiseLat;
//        String locationDesc = LocationConvertUtil.getLocationDesc(loc);
        String locationDesc = LocationConvertUtil.getLocationDesc(noiseLoc);

        Map<String,String> params = new HashMap<>();
//        params.put("locationLong",longitude);
//        params.put("locationLat",latitude);
        params.put("locationLong",noiseLong);
        params.put("locationLat",noiseLat);
        params.put("locationDesc",locationDesc);
        params.put("workerId", String.valueOf(session.getAttribute("userid")));
        int i = locationMapper.updateLocation(params);
        return new ResultVo(true, StatusCode.OK,"操作成功");
    }

    @RequestMapping("/loc/updateLoc/")
    @ResponseBody
    public ResultVo updateLoc(){
        return new ResultVo(true, StatusCode.OK,"操作成功");
    }
}
