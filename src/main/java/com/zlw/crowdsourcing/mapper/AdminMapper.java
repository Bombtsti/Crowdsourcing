package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface AdminMapper extends BaseMapper<Admin> {

    //查询
    List<Admin> selectAdmins();
    Admin selectAdminById(String id);
    //增加
    int insertAdmin(Admin admin);
    //删除
    int deleteAdmin(String id);
    //修改
    int updateAdmin(Admin admin);

}
