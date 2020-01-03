package com.ics.cloud.icsapi.api.user.bean;

import com.ics.cloud.common.bean.JWT;
import com.ics.cloud.common.model.Sys_user;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginBean {
    private JWT jwt;
    private Sys_user user;
    private String login_token;
}
