package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Sys_roleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sys_role record);

    int insertSelective(Sys_role record);

    Sys_role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sys_role record);

    int updateByPrimaryKey(Sys_role record);

    @Select("select id,role,role_name,description,create_date " +
            "from sys_role where status = 1")
    List<Sys_role> queryAll();
}