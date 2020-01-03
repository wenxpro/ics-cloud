package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_organization;
import com.ics.cloud.common.system.bean.SysOrgTreeBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Sys_organizationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sys_organization record);

    int insertSelective(Sys_organization record);

    Sys_organization selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sys_organization record);

    int updateByPrimaryKey(Sys_organization record);

    @Select("select id,name,parent_id,description,create_date " +
            "from sys_organization where status = 1")
    List<Sys_organization> queryAll();

    @Select("SELECT\n" +
            "\ta.id,a. NAME,a.parent_id,a.description,a.create_date,b.name parent_name\n" +
            "FROM sys_organization a left join sys_organization b on a.parent_id = b.id\n" +
            "where a.`status` = 1 and b.`status` = 1\n" +
            "union all \n" +
            "SELECT id,NAME,'system',description,create_date,'系统菜单'\n" +
            "FROM sys_organization where `status` = 1 and parent_id = 'system' \n" +
            "order by create_date desc")
    List<SysOrgTreeBean> queryAllList();
}