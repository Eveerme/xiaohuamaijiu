package com.chen.config;

import com.chen.utils.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
 * @Classname sdad
 * @Description TODO
 * @Date 2024/3/21 16:48
 * @Created by yx310
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 注册自定义拦截器
     * @param registry ·
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .excludePathPatterns("/userInfos/login") // 放行登录接口地址，登录不需要拦截
                .addPathPatterns("/api/**");// 拦截所有非登录的的接口地址
    }
}
