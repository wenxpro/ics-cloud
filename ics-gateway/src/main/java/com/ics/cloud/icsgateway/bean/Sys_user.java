package com.ics.cloud.icsgateway.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Sys_user {

    private String id;

    private String username;

    private String password;

    private String nickname;

    private String mobile;

    private String email;

    private String access_token;

    private String refresh_token;

    private Date create_date;

    private String create_userid;

    private Date del_date;

    private String del_uerid;

    private Integer status;
}
