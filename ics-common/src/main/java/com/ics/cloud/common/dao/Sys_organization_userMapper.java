package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_organization_user;

public interface Sys_organization_userMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sys_organization_user record);

    int insertSelective(Sys_organization_user record);

    Sys_organization_user selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sys_organization_user record);

    int updateByPrimaryKey(Sys_organization_user record);
}