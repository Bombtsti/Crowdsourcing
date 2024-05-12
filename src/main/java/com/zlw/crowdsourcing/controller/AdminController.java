package com.zlw.crowdsourcing.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zlw.crowdsourcing.mapper.EmployerMapper;
import com.zlw.crowdsourcing.mapper.WorkerMapper;
import com.zlw.crowdsourcing.pojo.Employer;
import com.zlw.crowdsourcing.utils.LaplaceUtil;
import com.zlw.crowdsourcing.utils.LocationConvertUtil;
import com.zlw.crowdsourcing.vo.WorkerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Controller
public class AdminController {

    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private EmployerMapper employerMapper;

    @RequestMapping("/admin/getworkerList")
    public String getworkerList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<WorkerVo> workers = workerMapper.selectWorkerVo();

//        添加噪声
//        for (WorkerVo worker: workers){
//            String locationLong = worker.getLocationLong();
//            String locationLat = worker.getLocationLat();
//            JSONObject pos = new JSONObject();
//            pos.put("latitude", Double.parseDouble(locationLat));
//            pos.put("longitude",Double.parseDouble(locationLong));
//            JSONObject noisePos = LaplaceUtil.addNoise(0.1, pos);
//            String noiseLong = String.valueOf(noisePos.get("longitude"));
//            String noiseLat = String.valueOf(noisePos.get("latitude"));
//            String LongAndLat = noiseLong+","+noiseLat;
//            String noisePosDesc = LocationConvertUtil.getLocationDesc(LongAndLat);
//            worker.setLocationLat(noiseLat);
//            worker.setLocationLong(noiseLong);
//            worker.setLocationDesc(noisePosDesc);
//        }

        PageHelper.startPage(pageNum,10);
        PageInfo<WorkerVo> workerPageInfo = new PageInfo<>(workers);
        model.addAttribute("workerPageInfo",workerPageInfo);
        return "admin/worker-list";
    }
    @RequestMapping("/admin/getemployerList")
    public String getemployerList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Employer> employers = employerMapper.selectEmployers();
        PageHelper.startPage(pageNum,10);
        PageInfo<Employer> employerPageInfo  = new PageInfo<>(employers);
        model.addAttribute("employerPageInfo",employerPageInfo);
        return "admin/employer-list";
    }

}
