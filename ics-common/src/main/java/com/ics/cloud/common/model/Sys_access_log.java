package com.ics.cloud.common.model;

import java.util.Date;

public class Sys_access_log {
    private String rid;

    private String optclasses;

    private String optmethod;

    private String optkey;

    private Date createtime;

    private Date complettime;

    private String opthostip;

    private String opthostname;

    private String optusername;

    private String optdeptname;

    private String optuserid;

    private String optdeptid;

    private String sessionid;

    private String optcontent;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getOptclasses() {
        return optclasses;
    }

    public void setOptclasses(String optclasses) {
        this.optclasses = optclasses == null ? null : optclasses.trim();
    }

    public String getOptmethod() {
        return optmethod;
    }

    public void setOptmethod(String optmethod) {
        this.optmethod = optmethod == null ? null : optmethod.trim();
    }

    public String getOptkey() {
        return optkey;
    }

    public void setOptkey(String optkey) {
        this.optkey = optkey == null ? null : optkey.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getComplettime() {
        return complettime;
    }

    public void setComplettime(Date complettime) {
        this.complettime = complettime;
    }

    public String getOpthostip() {
        return opthostip;
    }

    public void setOpthostip(String opthostip) {
        this.opthostip = opthostip == null ? null : opthostip.trim();
    }

    public String getOpthostname() {
        return opthostname;
    }

    public void setOpthostname(String opthostname) {
        this.opthostname = opthostname == null ? null : opthostname.trim();
    }

    public String getOptusername() {
        return optusername;
    }

    public void setOptusername(String optusername) {
        this.optusername = optusername == null ? null : optusername.trim();
    }

    public String getOptdeptname() {
        return optdeptname;
    }

    public void setOptdeptname(String optdeptname) {
        this.optdeptname = optdeptname == null ? null : optdeptname.trim();
    }

    public String getOptuserid() {
        return optuserid;
    }

    public void setOptuserid(String optuserid) {
        this.optuserid = optuserid == null ? null : optuserid.trim();
    }

    public String getOptdeptid() {
        return optdeptid;
    }

    public void setOptdeptid(String optdeptid) {
        this.optdeptid = optdeptid == null ? null : optdeptid.trim();
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid == null ? null : sessionid.trim();
    }

    public String getOptcontent() {
        return optcontent;
    }

    public void setOptcontent(String optcontent) {
        this.optcontent = optcontent == null ? null : optcontent.trim();
    }
}