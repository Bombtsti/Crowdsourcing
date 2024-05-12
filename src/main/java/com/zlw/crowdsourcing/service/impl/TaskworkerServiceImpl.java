package com.zlw.crowdsourcing.service.impl;

import com.zlw.crowdsourcing.pojo.Taskworker;
import com.zlw.crowdsourcing.mapper.TaskworkerMapper;
import com.zlw.crowdsourcing.service.ITaskworkerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2022-04-09
 */
@Service
public class TaskworkerServiceImpl extends ServiceImpl<TaskworkerMapper, Taskworker> implements ITaskworkerService {

}
