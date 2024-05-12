package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Taskworker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlw.crowdsourcing.vo.TaskVo;
import com.zlw.crowdsourcing.vo.WorkerTaskVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2022-04-09
 */
@Repository
public interface TaskworkerMapper extends BaseMapper<Taskworker> {

    int insertTaskWorker(Taskworker taskworker);
    List<WorkerTaskVo> selectTaskVoByWorkerId(String id);
    int updateStatus(@Param("orderStatus") String orderStatus,@Param("taskId") String taskId,@Param("workerId") String workerId);
}
