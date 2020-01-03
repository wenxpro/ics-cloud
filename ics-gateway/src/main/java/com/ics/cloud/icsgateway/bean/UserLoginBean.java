package com.ics.cloud.icsgateway.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginBean {
    private JWT jwt;
    private Sys_user user;
}
