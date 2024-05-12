package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Admin;
import com.zlw.crowdsourcing.pojo.ResData;
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
public interface ResDataMapper extends BaseMapper<ResData> {

    //查询
    List<ResData> selectResDatas();
    ResData selectResDataById(String id);
    List<ResData> getResDataByTaskId(String id);
    //增加
    int insertResData(ResData resData);
    //删除
    int deleteResData(String id);
    //修改
    int updateResData(ResData resData);
}
