package com.ics.cloud.common.system.service;

import com.ics.cloud.common.dao.Sys_access_logMapper;
import com.ics.cloud.common.system.bean.SysLogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogService {

    @Autowired
    private Sys_access_logMapper logMapper;

    public List<SysLogBean> queryLogCount(){
        return logMapper.queryLogCount();
    }
}
