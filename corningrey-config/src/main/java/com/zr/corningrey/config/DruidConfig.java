package com.zr.corningrey.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heqifeng
 */
@Configuration
public class DruidConfig {

    /**
     * 配置Druid监控
     * 1. 配置一个管理后台的Servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //启动后，可通过http://localhost:8080/druid/index.html访问Druid监控控制台
        Map<String, String> initParams = new HashMap<>(8);
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        //传空串，则默认允许所有访问
        initParams.put("allow", "");
        initParams.put("deny", "");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 2. 配置一个监控的filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = Maps.newHashMapWithExpectedSize(4);
        initParams.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
