
package com.zlw.crowdsourcing.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zlw.crowdsourcing.mapper.OrderMapper;
import com.zlw.crowdsourcing.mapper.ResDataMapper;
import com.zlw.crowdsourcing.mapper.TaskMapper;
import com.zlw.crowdsourcing.mapper.TaskworkerMapper;
import com.zlw.crowdsourcing.pojo.Order;
import com.zlw.crowdsourcing.pojo.ResData;
import com.zlw.crowdsourcing.pojo.Task;
import com.zlw.crowdsourcing.utils.KeyUtil;
import com.zlw.crowdsourcing.vo.ResultVo;
import com.zlw.crowdsourcing.vo.StatusCode;
import com.zlw.crowdsourcing.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
public class ResDataController {

    @Autowired
    private ResDataMapper resDataMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private TaskworkerMapper taskworkerMapper;

    @RequestMapping("/data/getResDataByTaskId/{id}")
    @ResponseBody
    public ResultVo getResDataByTaskId(@PathVariable("id") String id){
        //PageHelper.startPage(pageNum,1);
        List<ResData> resDatas = resDataMapper.getResDataByTaskId(id);
        //PageInfo<ResData> pageInfo = new PageInfo<>(resDatas);
        return new ResultVo(true, StatusCode.OK,"查询成功",resDatas);
    }

    @PostMapping("/data/submitData")
    @ResponseBody
    public ResultVo submitData(@RequestBody ResData resData, HttpSession session){
        String dataId = "d"+ KeyUtil.genUniqueKey();
        String dataGrade = resData.getDataGrade();
        String dataDesc = resData.getDataDesc();
        String dataImg = resData.getDataImg();
        String taskId = resData.getTaskId();
        String workerId = String.valueOf(session.getAttribute("userid"));
        ResData data = new ResData(dataId,dataGrade,dataDesc,dataImg,taskId,workerId);

        //添加到数据库
        //开启事物
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);

        int i = resDataMapper.insertResData(data);
        int j = taskworkerMapper.updateStatus("1", taskId, workerId);

        if (i > 0 && j > 0){
            transactionManager.commit(transStatus);
            return new ResultVo(true,StatusCode.OK,"操作成功");
        }else{
            transactionManager.rollback(transStatus);
            return new ResultVo(false,StatusCode.ERROR,"操作失败");
        }
    }
}
