package com.ics.cloud.common.bean;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * <ul>bean处理</ul>
 * <li>new HandleBean().post() 添加前处理</li>
 * <li>new HandleBean().delete() 删除前处理</li>
 */
@Getter
@Setter
public class HandleBean {

    private String id;

    private String create_userid;

    private Date create_date;

    private Integer status;

    private String del_uerid;

    private Date del_date;

    /**
     * insert handle
     *
     * @param userid
     * @return
     */
    public HandleBean post(String userid) {
        this.id = IdUtil.fastUUID();
        this.create_userid = userid;
        this.create_date = DateUtil.date();
        this.status = 1;
        return this;
    }

    /**
     * insert handle 不处理用户id
     *
     * @return
     */
    public HandleBean post() {
        this.id = IdUtil.fastUUID();
        this.create_date = DateUtil.date();
        this.status = 1;
        return this;
    }

    /**
     * delete handle
     *
     * @param userid
     * @return
     */
    public HandleBean delete(String id, String userid) {
        this.id = id;
        this.del_uerid = userid;
        this.del_date = DateUtil.date();
        this.status = 0;
        return this;
    }
}