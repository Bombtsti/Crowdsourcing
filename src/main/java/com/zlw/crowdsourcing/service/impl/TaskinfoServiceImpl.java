package com.zlw.crowdsourcing.service.impl;

import com.zlw.crowdsourcing.pojo.Order;
import com.zlw.crowdsourcing.mapper.OrderMapper;
import com.zlw.crowdsourcing.service.ITaskinfoService;
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
public class TaskinfoServiceImpl extends ServiceImpl<OrderMapper, Order> implements ITaskinfoService {

}
