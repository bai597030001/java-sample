package com.example.shiroexample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: java-sample
 * @description: 配置器
 * @author: baijd-a
 * @create: 2020-07-15 10:40
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ShiroHandlerInterceptorAdapter())
                .addPathPatterns("/admin/*")
                .addPathPatterns("/user/*")
                .excludePathPatterns("/")
                .excludePathPatterns("/register/*")
                .excludePathPatterns("/login/*")
                .excludePathPatterns("/error/*");
    }
}
