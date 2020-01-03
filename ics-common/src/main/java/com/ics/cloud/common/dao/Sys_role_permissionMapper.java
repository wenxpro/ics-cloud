package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_role_permission;

public interface Sys_role_permissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sys_role_permission record);

    int insertSelective(Sys_role_permission record);

    Sys_role_permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sys_role_permission record);

    int updateByPrimaryKey(Sys_role_permission record);
}