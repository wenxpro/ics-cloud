package com.ics.cloud.common.model;

import java.util.Date;

public class Sys_organization_user {
    private String id;

    private String organization_id;

    private String user_id;

    private Date create_date;

    private String create_userid;

    private Date del_date;

    private String del_uerid;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id == null ? null : organization_id.trim();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_userid() {
        return create_userid;
    }

    public void setCreate_userid(String create_userid) {
        this.create_userid = create_userid == null ? null : create_userid.trim();
    }

    public Date getDel_date() {
        return del_date;
    }

    public void setDel_date(Date del_date) {
        this.del_date = del_date;
    }

    public String getDel_uerid() {
        return del_uerid;
    }

    public void setDel_uerid(String del_uerid) {
        this.del_uerid = del_uerid == null ? null : del_uerid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}