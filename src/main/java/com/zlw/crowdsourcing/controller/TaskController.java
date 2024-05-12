package com.zlw.crowdsourcing.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zlw.crowdsourcing.mapper.*;
import com.zlw.crowdsourcing.pojo.*;
import com.zlw.crowdsourcing.utils.KeyUtil;
import com.zlw.crowdsourcing.utils.LocationConvertUtil;
import com.zlw.crowdsourcing.vo.ResultVo;
import com.zlw.crowdsourcing.vo.StatusCode;
import com.zlw.crowdsourcing.vo.TaskVo;
import jdk.net.SocketFlow;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Controller
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private TaskworkerMapper taskworkerMapper;
    @Autowired
    private OrderMapper orderMapper;

    //增加任务
    @PostMapping("/task/addTask")
    @ResponseBody
    public ResultVo addTask(@RequestBody TaskVo taskVo, HttpSession session){
        //获取前端数据
        String taskId = "t"+KeyUtil.genUniqueKey();
        LocalDateTime taskStartTime = taskVo.getTaskStartTime();
        LocalDateTime taskEndTime = taskVo.getTaskEndTime();
        String taskDesc = taskVo.getTaskDesc();
        String taskIncentive = taskVo.getTaskIncentive();
        String taskWorNum = taskVo.getTaskWorNum();
        String locationDesc = taskVo.getLocationDesc();
        String userid = String.valueOf(session.getAttribute("userid"));
        LocalDateTime time = LocalDateTime.now();

        //调用高德地图api将位置转为经纬度
        String LongAndLat = LocationConvertUtil.getLnglat(locationDesc);
        String[] str = LongAndLat.split("\\,");
        String locationId = "l"+KeyUtil.genUniqueKey();
        String locationLong = str[0];
        String locationLat = str[1];

        //任务分配
        List<Worker> workers = workerMapper.selectWorkers();
        double taskLong = Double.parseDouble(locationLong);
        double taskLat = Double.parseDouble(locationLat);
        GlobalCoordinates taskLoc = new GlobalCoordinates(taskLat,taskLong);
        //map存储工人以及距离
        Map<Worker, Double> map = new HashMap<>();
        for (Worker worker:workers){
            Location location = locationMapper.selectLocationById(worker.getLocationId());
            double workerLong = Double.parseDouble(location.getLocationLong());
            double workerLat = Double.parseDouble(location.getLocationLat());
            GlobalCoordinates workerLoc = new GlobalCoordinates(workerLat,workerLong);
            //计算工人和任务地点之间距离
            GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, taskLoc, workerLoc);
            double distance = geoCurve.getEllipsoidalDistance();
            map.put(worker,distance);
        }
        //按照value对map排序
        List<Map.Entry<Worker, Double>> list_Data = new ArrayList<Map.Entry<Worker, Double>>(map.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<Worker, Double>>() {
            public int compare(Map.Entry<Worker, Double> o1, Map.Entry<Worker, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });


        //封装task对象
        Task task = new Task(taskId,taskStartTime,taskEndTime,taskDesc,taskIncentive,taskWorNum,locationId,userid,time,null,"0");
        //封装Location对象
        Location location = new Location(locationId,locationLong,locationLat,locationDesc);

        //添加到数据库
        //开启事物
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);
        try {
            int i = locationMapper.insertLocation(location);
            int j = taskMapper.insertTask(task);

            int twsize = 0;
            if (list_Data.size() >= Integer.parseInt(taskWorNum)){
                twsize = Integer.parseInt(taskWorNum);
            }else{
                twsize = list_Data.size();
            }
            //工人与任务的映射关系,分配任务
            for (int k = 0; k < twsize; k++){
//            System.out.println(list_Data.get(k).getKey());
                Worker worker = list_Data.get(k).getKey();
                Taskworker taskworker = new Taskworker(taskId,worker.getWorkerId(),"0");
                //一个工人对应一个订单
//                String orderId = "o"+KeyUtil.genUniqueKey();
//                Order order = new Order(orderId,time,null,"0",worker.getWorkerId(),taskId,userid);
                //添加数据库
                taskworkerMapper.insertTaskWorker(taskworker);
                //orderMapper.insertTaskinfo(order);
            }
            //提交事务
            transactionManager.commit(transStatus);
            return new ResultVo(true, StatusCode.OK,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(transStatus);
            return new ResultVo(false,StatusCode.ERROR,"添加失败");
        }
    }

    //获取所有任务
    @RequestMapping("/task/getAllTaskVo")
    public String getAllTask(Model model,HttpSession session, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        String userid = String.valueOf(session.getAttribute("userid"));
        List<TaskVo> taskVos = taskMapper.selectTaskVos(userid);
        PageInfo<TaskVo> pageInfo = new PageInfo<>(taskVos);
        model.addAttribute("pageInfo",pageInfo);
        return "demand-list";
    }

    //删除task根据id
    @RequestMapping("/task/deleteTaskById/{id}")
    @ResponseBody
    public ResultVo deleteTaskById(@PathVariable("id") String id){
        System.out.println(id);
        Task task = taskMapper.selectTaskById(id);
        //开启事物
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);
        //删除订单
        int i = taskMapper.deleteTask(id);
        if (i>0){
            transactionManager.commit(transStatus);
            return new ResultVo(true, StatusCode.OK,"删除成功");
        }else{
            transactionManager.rollback(transStatus);
            return new ResultVo(false,StatusCode.ERROR,"删除失败");
        }
    }

    //查询taskvo根据taskId
    @RequestMapping("/task/selectTaskVoById/{id}")
    @ResponseBody
    public ResultVo selectTaskVoById(@PathVariable("id") String id){
        TaskVo taskVo = taskMapper.selectTaskVoById(id);
        if (taskVo!=null){
            return new ResultVo(true, StatusCode.OK,"查找成功",taskVo);
        }else{
            return new ResultVo(false,StatusCode.ERROR,"查找失败");
        }
    }

}
