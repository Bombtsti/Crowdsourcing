package com.zlw.crowdsourcing.controller;

import com.alibaba.fastjson.JSONObject;
import com.zlw.crowdsourcing.mapper.AdminMapper;
import com.zlw.crowdsourcing.mapper.EmployerMapper;
import com.zlw.crowdsourcing.mapper.LocationMapper;
import com.zlw.crowdsourcing.mapper.WorkerMapper;
import com.zlw.crowdsourcing.pojo.Admin;
import com.zlw.crowdsourcing.pojo.Employer;
import com.zlw.crowdsourcing.pojo.Location;
import com.zlw.crowdsourcing.pojo.Worker;
import com.zlw.crowdsourcing.utils.*;
import com.zlw.crowdsourcing.vo.ResultVo;
import com.zlw.crowdsourcing.vo.StatusCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private EmployerMapper employerMapper;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;

    @PostMapping("/user/register")
    public ResultVo register(@RequestBody Map<String,String> user){
        String role = user.get("role");
        String id = user.get("id");
        String name = user.get("name");
//        String pwd = user.get("pwd");
        String pwd = PwdUtil.md5PlusSalt(user.get("pwd"));
        String phone = user.get("phone");
        String loc = user.get("loc");
        if ("用户".equals(role)){
            Employer employer1 = employerMapper.selectEmployerById(id);
            if (employer1!=null){
                return new ResultVo(false,StatusCode.ERROR,"账号已存在");
            }
            Employer employer = new Employer(id,name,pwd,phone);
            int i = employerMapper.insertEmployer(employer);
            if (i>0){
                return new ResultVo(true, StatusCode.OK,"注册成功");
            }else{
                return new ResultVo(false, StatusCode.ERROR,"注册失败");
            }
        }else if("工人".equals(role)){
            Worker worker1 = workerMapper.selectWorkerById(id);
            if (worker1!=null){
                return new ResultVo(false,StatusCode.ERROR,"账号已存在");
            }
            String locationId = "l"+KeyUtil.genUniqueKey();
            String locationDesc = LocationConvertUtil.getLocationDesc(loc);
            String[] str = loc.split("\\,");
            String longitude = str[0];
            String latitude = str[1];

            //添加噪声
            JSONObject pos = new JSONObject();
            pos.put("latitude",Double.parseDouble(latitude));
            pos.put("longitude",Double.parseDouble(longitude));
            JSONObject noisePos = LaplaceUtil.addNoise(0.04, pos);
            String noiseLong = String.valueOf(noisePos.get("longitude"));
            String noiseLat = String.valueOf(noisePos.get("latitude"));

            String noiseLoc = noiseLong+","+noiseLat;
//        String locationDesc = LocationConvertUtil.getLocationDesc(loc);
            String noiselocDesc = LocationConvertUtil.getLocationDesc(noiseLoc);

            //封装实体类
//            Location location = new Location(locationId, longitude, latitude, locationDesc);
            Location location = new Location(locationId, noiseLong, noiseLat, noiselocDesc);
            Worker worker = new Worker(id,name,pwd,phone,"5",locationId);
            //开启事务
            DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
            transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);
            int i = locationMapper.insertLocation(location);
            int j = workerMapper.insertWorker(worker);
            if (i>0 && j>0){
                //提交事务
                transactionManager.commit(transStatus);
                return new ResultVo(true, StatusCode.OK,"注册成功");
            }else{
                //回退事务
                transactionManager.rollback(transStatus);
                return new ResultVo(false, StatusCode.ERROR,"注册失败");
            }
        } else{
            return new ResultVo(false, StatusCode.ERROR,"注册失败");
        }
    }

    @PostMapping("/user/login")
    public ResultVo login(@RequestBody Map<String,String> user, HttpSession session){
        String userid = user.get("userid");
        String password = user.get("password");
        String MD5pwd = PwdUtil.md5PlusSalt(password);
        String flag = "";
        if (!"".equals(userid)){
            flag = userid.substring(0,1);
        }
        if ("e".equals(flag)){
            Employer employer = employerMapper.selectEmployerById(userid);
            if (employer.getEmployerPwd().equals(MD5pwd)){
                session.setAttribute("userid",employer.getEmployerId());
                session.setAttribute("username",employer.getEmployerName());
                return new ResultVo(true, StatusCode.OK,"employer");
            }else {
                return new ResultVo(false, StatusCode.ERROR,"error");
            }
        }else if ("w".equals(flag)){
            Worker worker = workerMapper.selectWorkerById(userid);
            if (worker.getWorkerPwd().equals(MD5pwd)){
                session.setAttribute("userid",worker.getWorkerId());
                session.setAttribute("username",worker.getWorkerName());
                return new ResultVo(true,StatusCode.OK,"worker");
            }else {
                return new ResultVo(false, StatusCode.ERROR,"error");
            }
        }else {
            Admin admin = adminMapper.selectAdminById(userid);
            if (admin.getAdminPwd().equals(password)){
                session.setAttribute("userid",admin.getAdminId());
                session.setAttribute("username",admin.getAdminName());
                return new ResultVo(true,StatusCode.OK,"admin");
            }else{
                return new ResultVo(false,StatusCode.ERROR,"error");
            }
        }
    }
}
