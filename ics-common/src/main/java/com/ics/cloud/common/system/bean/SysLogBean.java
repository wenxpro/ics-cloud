package com.ics.cloud.common.system.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SysLogBean {

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

    private String optcontent;

    private String optuserid;

    private String optdeptid;

    private String sessionid;

    private Integer remotecount;
}
