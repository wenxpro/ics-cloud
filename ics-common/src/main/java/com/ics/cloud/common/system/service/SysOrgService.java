package com.ics.cloud.common.system.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ics.cloud.common.base.BaseCrudMapper;
import com.ics.cloud.common.dao.Sys_organizationMapper;
import com.ics.cloud.common.model.Sys_organization;
import com.ics.cloud.common.system.bean.SysOrgTreeBean;
import com.ics.cloud.common.util.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SysOrgService implements BaseCrudMapper<Sys_organization> {

    @Autowired
    private Sys_organizationMapper organizationMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return organizationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Sys_organization record) {
        return organizationMapper.insertSelective(record);
    }

    @Override
    public Sys_organization selectByPrimaryKey(String id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Sys_organization record) {
        return organizationMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询分页或者条件
     *
     * @param pageNum
     * @param pageSize
     * @param pageFlag
     * @return
     */
    public PageInfo<Sys_organization> queryAll(Integer pageNum, Integer pageSize, boolean pageFlag) {
        if (pageFlag) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Sys_organization> list = organizationMapper.queryAll();
        return new PageInfo<>(list);
    }

    /**
     * 查询treelist
     *
     * @return
     */
    public List<SysOrgTreeBean> queryTreeList() {
        List<SysOrgTreeBean> list = organizationMapper.queryAllList();
        if (ListUtil.is(list)) {
            //一级
            List<SysOrgTreeBean> sysOrgTreeBeans = new LinkedList<>();
            list.forEach(organization -> {
                if (organization.getParent_id().equals("system")) {
                    SysOrgTreeBean bean = new SysOrgTreeBean();
                    BeanUtils.copyProperties(organization, bean);
                    sysOrgTreeBeans.add(convertTree(bean, list));
                }
            });
            return sysOrgTreeBeans;
        }
        return null;
    }

    private SysOrgTreeBean convertTree(SysOrgTreeBean bean, List<SysOrgTreeBean> list) {
        list.forEach(organization -> {
            if (bean.getId().equals(organization.getParent_id())) {
                if (bean.getChildren() == null) {
                    bean.setChildren(new LinkedList<>());
                }
                SysOrgTreeBean addBean = new SysOrgTreeBean();
                BeanUtils.copyProperties(organization, addBean);
                bean.getChildren().add(convertTree(addBean, list));
            }
        });
        return bean;
    }
}
