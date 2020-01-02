package com.ics.cloud.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("返回数据实体")
public class BaseRetBean {
    /**
     * 返回代码，0 失败，1成功 -1异常 2 会话超时 3 参数错误 4 权限错误 5 token为空 6 token错误 11 结果不一致
     */
    @ApiModelProperty(value = "返回代码，0 失败，1成功 -1异常 2 会话超时 3 参数错误 4 权限错误 5 access_token为空 6 access_token错误 11 结果不一致")
    private int ret = 0;

    @ApiModelProperty(value = "返回消息")
    private String msg;
    @ApiModelProperty(value = "返回数据内容（Json格式）")
    private Object data;

    public BaseRetBean() {
    }

    public BaseRetBean(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }
}
