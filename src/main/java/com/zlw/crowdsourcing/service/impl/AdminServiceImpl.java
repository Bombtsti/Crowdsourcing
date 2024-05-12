package com.zlw.crowdsourcing.service.impl;

import com.zlw.crowdsourcing.pojo.Admin;
import com.zlw.crowdsourcing.mapper.AdminMapper;
import com.zlw.crowdsourcing.service.IAdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
