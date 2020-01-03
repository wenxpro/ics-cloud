package com.ics.cloud.icsgateway.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseRetBean {
    /**
     * 返回代码，0 失败，1成功 -1异常 2 会话超时 3 参数错误 4 权限错误 5 token为空 6 token错误 11 结果不一致
     */
    private int ret = 0;

    private String msg;
    private Object data;

    public BaseRetBean() {
    }

    public BaseRetBean(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }
}
