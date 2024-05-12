package com.zlw.crowdsourcing.service.impl;

import com.zlw.crowdsourcing.pojo.Worker;
import com.zlw.crowdsourcing.mapper.WorkerMapper;
import com.zlw.crowdsourcing.service.IWorkerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements IWorkerService {

}
