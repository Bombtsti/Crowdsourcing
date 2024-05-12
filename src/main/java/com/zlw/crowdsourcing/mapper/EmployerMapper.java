package com.zlw.crowdsourcing.mapper;

import com.zlw.crowdsourcing.pojo.Employer;
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
public interface EmployerMapper extends BaseMapper<Employer> {
    //查询
    List<Employer> selectEmployers();
    Employer selectEmployerById(String id);
    //增加
    int insertEmployer(Employer employer);
    //删除
    int deleteEmployer(String id);
    //修改
    int updateEmployer(Employer employer);

}
