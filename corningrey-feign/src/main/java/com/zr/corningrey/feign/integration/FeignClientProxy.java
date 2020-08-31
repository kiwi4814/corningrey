package com.zr.corningrey.user.integration;

import com.zr.corningrey.user.entity.DemoResp;
import com.zr.corningrey.user.entity.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(name = "feignClientProxy",url = "http://wuwenliang.net/samples/")
public interface FeignClientProxy {

    @RequestMapping(value = "/snowalker.json", method = RequestMethod.GET)
    @ResponseBody
    ResponseData<DemoResp> invoke(@RequestParam(value="name") String name);

    /**
     * 容错处理类，当调用失败时 返回空字符串
     */
    @Component
    class DefaultFallback implements FeignClientProxy {
        @Override
        public ResponseData<DemoResp> invoke(@RequestParam(value="snowalker") String name){
            return new ResponseData<DemoResp>().setCode("40004").setDesc("服务异常").setData(null);
        }
    }
}
