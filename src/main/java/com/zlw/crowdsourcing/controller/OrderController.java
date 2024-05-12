package com.zlw.crowdsourcing.controller;


import com.zlw.crowdsourcing.mapper.OrderMapper;
import com.zlw.crowdsourcing.pojo.Order;
import com.zlw.crowdsourcing.vo.ResultVo;
import com.zlw.crowdsourcing.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Controller
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;

    //删除订单根据id
    @RequestMapping("/order/deleteOrderById/{id}")
    @ResponseBody
    public ResultVo deleteTaskById(@PathVariable("id") String id){
        System.out.println(id);
        Order task = orderMapper.selectTaskinfoById(id);
        //开启事物
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);
        //删除订单
        int i = orderMapper.deleteTaskinfo(id);
        //task？ resdata？ location？
        if (i>0){
            transactionManager.commit(transStatus);
            return new ResultVo(true, StatusCode.OK,"删除成功");
        }else{
            transactionManager.rollback(transStatus);
            return new ResultVo(false,StatusCode.ERROR,"删除失败");
        }
    }
}
