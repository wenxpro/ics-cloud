package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_access_log;
import com.ics.cloud.common.system.bean.SysLogBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Sys_access_logMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Sys_access_log record);

    int insertSelective(Sys_access_log record);

    Sys_access_log selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Sys_access_log record);

    int updateByPrimaryKeyWithBLOBs(Sys_access_log record);

    int updateByPrimaryKey(Sys_access_log record);

    @Select("select count(1) remotecount,a.optmethod from sys_access_log a GROUP BY a.optmethod order by remotecount desc limit 10")
    List<SysLogBean> queryLogCount();
}