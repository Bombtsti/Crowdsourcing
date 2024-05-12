package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Admin;
import com.zlw.crowdsourcing.pojo.Worker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlw.crowdsourcing.vo.WorkerVo;
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
public interface WorkerMapper extends BaseMapper<Worker> {
    //查询
    List<Worker> selectWorkers();
    List<WorkerVo> selectWorkerVo();
    Worker selectWorkerById(String id);
    //增加
    int insertWorker(Worker worker);
    //删除
    int deleteWorker(String id);
    //修改
    int updateWorker(Worker worker);
}
