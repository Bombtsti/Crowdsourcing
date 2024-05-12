package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Admin;
import com.zlw.crowdsourcing.pojo.Location;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Repository
public interface LocationMapper extends BaseMapper<Location> {
    //查询
    Location selectLocationById(String id);
    //增加
    int insertLocation(Location location);
    //删除
    int deleteLocation(String id);
    //修改
    int updateLocation(Map<String,String> params);
}
