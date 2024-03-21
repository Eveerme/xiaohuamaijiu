package com.chen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class xiaohuamaijiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(xiaohuamaijiuApplication.class, args);
    }

    // 将restTemplate加入Ioc容器中，用于向微信服务器发送请求获取openId
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
