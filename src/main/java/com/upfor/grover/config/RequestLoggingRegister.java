package com.upfor.grover.config;

import com.upfor.grover.filter.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class RequestLoggingRegister {

    /**
     * 注册请求日志打印
     */
    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilterRegistration() {
        FilterRegistrationBean<RequestLoggingFilter> registration = new FilterRegistrationBean<>();
        RequestLoggingFilter filter = new RequestLoggingFilter();
        // Query String
        filter.setIncludeQueryString(true);
        // 请求体
        filter.setIncludePayload(true);
        // 最大请求体长度
        filter.setMaxPayloadLength(9999);
        // 响应体
        filter.setIncludeResponseBody(true);

        registration.setFilter(filter);
        // 设置打印日志的请求
        registration.setUrlPatterns(Collections.singleton("/api/*"));
        return registration;
    }

}
