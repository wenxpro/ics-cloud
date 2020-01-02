package com.ics.cloud.common.base;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("返回分页数据实体")
public class BasePageRetBean extends BaseRetBean {

    private int total;

    private int offset;

    private int limit;

//    private Object rows;

    private Integer pageSize;

    private Integer pageNum;

    public BaseRetBean renderRet(PageInfo pageInfo) {
        this.total = (int) pageInfo.getTotal();
        this.offset = pageInfo.getStartRow();
        this.limit = pageInfo.getPageSize();
        this.setData(pageInfo.getList());
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        return this;
    }
}
