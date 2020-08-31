package com.zr.corningrey.feign.integration;

import com.zr.corningrey.feign.entity.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(name = "feignClientProxy", url = "http://192.168.1.202:8081/grandcanal-camundav2/")
public interface FeignClientProxy {

    @RequestMapping(value = "/api/statistics/findCommonData.json", method = RequestMethod.GET)
    @ResponseBody
    ResponseData invoke(@RequestParam(value = "tenantId") String tenantId);

    /**
     * 容错处理类，当调用失败时 返回空字符串
     */
    @Component
    class DefaultFallback implements FeignClientProxy {
        @Override
        public ResponseData invoke(@RequestParam(value = "tenantId") String name) {
            return ResponseData.builder().message("错误").data(null).build();
        }
    }
}
