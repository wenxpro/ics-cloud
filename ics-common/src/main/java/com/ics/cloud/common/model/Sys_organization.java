package com.ics.cloud.common.model;

import java.util.Date;

public class Sys_organization {
    private String id;

    private String name;

    private String parent_id;

    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id == null ? null : parent_id.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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