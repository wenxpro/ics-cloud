package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_permission;

public interface Sys_permissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sys_permission record);

    int insertSelective(Sys_permission record);

    Sys_permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sys_permission record);

    int updateByPrimaryKey(Sys_permission record);
}