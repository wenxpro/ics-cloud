package com.ics.cloud.common.util;

import com.github.pagehelper.PageInfo;

import java.util.List;


public class PageUtil {

    /**
     * 逻辑分页
     *
     * @param list
     * @param pageSize
     * @param pageNum
     * @param pageFlag
     * @return
     */
    public static PageInfo renderPage(List list, Integer pageSize, Integer pageNum, boolean pageFlag) {
        if (list == null) {
            return null;
        }
        PageInfo pageInfo = new PageInfo(list);
        if (pageFlag) {
            int totalCount = list.size();
            pageNum = pageNum - 1;
            int start = pageNum * pageSize;
            //分页不能大于总数
            if (start >= totalCount) {
                throw new RuntimeException("页数或分页大小不正确!");
            }
            int end = ((pageNum + 1) * pageSize);
            if (end > totalCount) {
                end = totalCount;
            }
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNum + 1);
            pageInfo.setTotal(list.size());
            pageInfo.setList(list.subList(start, end));
        } else {
            pageInfo.setList(list);
        }
        return pageInfo;
    }
}
