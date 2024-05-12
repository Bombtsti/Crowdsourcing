package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlw.crowdsourcing.vo.TaskVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
public interface TaskMapper extends BaseMapper<Task> {
    //查询
    List<Task> selectTasks();
    List<TaskVo> selectTaskVos(String id);
    Task selectTaskById(String id);
    TaskVo selectTaskVoById(String id);
    //增加
    int insertTask(Task task);
    //删除
    int deleteTask(String id);
    //修改
    int updateTask(Task task);
    int updateTask1(@Param("taskFinishTime") LocalDateTime finishTime, @Param("taskStatus") String status, @Param("taskId") String id);
}
