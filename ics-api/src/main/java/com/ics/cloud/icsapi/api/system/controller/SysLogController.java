package com.ics.cloud.icsapi.api.system.controller;


import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.common.system.bean.SysLogBean;
import com.ics.cloud.common.system.service.SysLogService;
import com.ics.cloud.common.util.ListUtil;
import com.ics.cloud.icsapi.common.controller.IcsApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
@Api(description = "系统日志接口")
@Slf4j
public class SysLogController extends IcsApiController {

    @Autowired
    private SysLogService logService;

    @ApiOperation("查询系统请求统计")
    @GetMapping("/queryLogCount")
    public BaseRetBean queryLogCount(){
        BaseRetBean baseRetBean = new BaseRetBean(1,"success");
        try {
            List<SysLogBean> list = logService.queryLogCount();
            if(ListUtil.is(list)){
                baseRetBean.setData(list);
            }
        }catch (Exception e){
            log.error("error:{}",e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }
}
