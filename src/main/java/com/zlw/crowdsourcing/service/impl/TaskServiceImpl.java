package com.zlw.crowdsourcing.service.impl;

import com.zlw.crowdsourcing.pojo.Task;
import com.zlw.crowdsourcing.mapper.TaskMapper;
import com.zlw.crowdsourcing.service.ITaskService;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
