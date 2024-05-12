package com.zlw.crowdsourcing.service.impl;

import com.zlw.crowdsourcing.pojo.Location;
import com.zlw.crowdsourcing.mapper.LocationMapper;
import com.zlw.crowdsourcing.service.ILocationService;
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
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements ILocationService {

}
