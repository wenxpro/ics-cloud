package com.ics.cloud.common.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ics.cloud.common.base.BaseCrudMapper;
import com.ics.cloud.common.dao.Sys_userMapper;
import com.ics.cloud.common.model.Sys_user;
import com.ics.cloud.common.system.bean.SysUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService implements BaseCrudMapper<Sys_user> {

    @Autowired
    private Sys_userMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Sys_user record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public Sys_user selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Sys_user record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据用户名查询用户
     *
     * @param name
     * @return
     */
    public Sys_user queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    /**
     * 添加
     *
     * @param user
     * @return
     */
    public boolean add(Sys_user user) {
        Sys_user user1 = queryUserByName(user.getUsername());
        if (user1 != null) {
            return false;
        }
        this.insertSelective(user);
        return true;
    }

    public PageInfo<SysUserBean> queryAll(Integer pageNum, Integer pageSize, boolean pageFlag) {
        if (pageFlag) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<SysUserBean> list = userMapper.queryAll();
        return new PageInfo<>(list);
    }
}
