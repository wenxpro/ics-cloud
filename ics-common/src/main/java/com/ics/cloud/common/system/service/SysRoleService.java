package com.ics.cloud.common.system.service;

import cn.hutool.core.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ics.cloud.common.base.BaseCrudMapper;
import com.ics.cloud.common.bean.HandleBean;
import com.ics.cloud.common.dao.Sys_roleMapper;
import com.ics.cloud.common.dao.Sys_user_roleMapper;
import com.ics.cloud.common.model.Sys_role;
import com.ics.cloud.common.model.Sys_user_role;
import com.ics.cloud.common.util.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService implements BaseCrudMapper<Sys_role> {

    @Autowired
    private Sys_roleMapper roleMapper;
    @Autowired
    private Sys_user_roleMapper user_roleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Sys_role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Sys_role selectByPrimaryKey(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Sys_role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    public PageInfo<Sys_role> queryAll(Integer pageNum, Integer pageSize, boolean pageFlag) {
        if (pageFlag) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Sys_role> list = roleMapper.queryAll();
        return new PageInfo<>(list);
    }

    public List<Sys_user_role> queryUserRoleByUserId(String userid){
        return user_roleMapper.queryListByUserId(userid);
    }

    public int insertUserRoleBatch(List<Sys_user_role> list){
        return user_roleMapper.insertBatch(list);
    }

    public int deleteUserRoleBatch(List<String> strings){
        return user_roleMapper.deleteBatch(strings);
    }

    public void saveUserRoleByUserid(String userid,String roles){
        //查询之前的角色并删除
        List<Sys_user_role> userRoles = this.queryUserRoleByUserId(userid);
        if(ListUtil.is(userRoles)){
            List<String> delList = new ArrayList<>();
            userRoles.forEach(userRole -> {
                delList.add(userRole.getId());
            });
            this.deleteUserRoleBatch(delList);
        }
        String[] array = roles.split(",");
        if(array.length > 0){
            //添加新的角色
            List<Sys_user_role> addList = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                Sys_user_role user_role = new Sys_user_role();
                BeanUtils.copyProperties(new HandleBean().post(),user_role);
                user_role.setUser_id(userid);
                user_role.setRole_id(array[i]);
                addList.add(user_role);
            }
            this.insertUserRoleBatch(addList);
        }
    }
}
