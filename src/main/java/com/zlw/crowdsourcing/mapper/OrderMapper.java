package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    //查询
    List<Order> selectTaskinfos();
    Order selectTaskinfoById(String id);
    Order selectTaskByTaskAndWorker(@Param("taskId") String taskId, @Param("workerId") String workerId);
    //增加
    int insertTaskinfo(Order order);
    //删除
    int deleteTaskinfo(String id);
    //修改
    int updateTaskinfo(Order order);
    int updateOrderStatus(@Param("orderStatus") String status,@Param("taskId") String taskId, @Param("workerId") String workerId);
}
