package com.zr.corningrey.feign.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zr.corningrey.feign.entity.ResponseData;
import com.zr.corningrey.feign.integration.FeignClientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/feign")
public class FeignDemoController {

    @Resource
    FeignClientProxy feignClientProxy;

    @RequestMapping(value = "feign")
    @ResponseBody
    public ResponseData testFeign(@RequestParam(name = "tenantId") String tenantId) throws JsonProcessingException {
        return feignClientProxy.invoke(tenantId);

    }
}
