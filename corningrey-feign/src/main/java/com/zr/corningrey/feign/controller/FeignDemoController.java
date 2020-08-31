package com.zr.corningrey.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zr.corningrey.user.entity.DemoResp;
import com.zr.corningrey.user.entity.ResponseData;
import com.zr.corningrey.user.integration.FeignClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class FeignDemoController {

    @Autowired
    FeignClientProxy feignClientProxy;

    @RequestMapping(value = "feign")
    @ResponseBody
    public String testFeign(@RequestParam(name = "name") String name) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData<DemoResp> respResponseBody = feignClientProxy.invoke(name);
        return "调用结果:" + objectMapper.writeValueAsString(respResponseBody);

    }
}
