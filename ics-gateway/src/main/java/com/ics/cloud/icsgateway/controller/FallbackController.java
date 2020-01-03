package com.ics.cloud.icsgateway.controller;

import com.ics.cloud.icsgateway.bean.ResultEnum;
import com.ics.cloud.icsgateway.bean.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用熔断器
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class FallbackController {


    @RequestMapping("/fallback")
    public ResultView fallback() {
        ResultView resultView = ResultView.error(ResultEnum.CODE_504);
        log.debug(resultView.toString());
        return resultView;
    }
}
