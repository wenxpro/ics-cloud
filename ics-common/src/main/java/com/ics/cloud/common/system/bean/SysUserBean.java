package com.ics.cloud.common.system.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SysUserBean {

    private String id;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date create_date;
    private String create_userid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date del_date;
    private String del_uerid;
    private Integer status;

}
