package com.study.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope  //只需要在需要动态读取配置的类上添加此注解就可以
@RequestMapping("/demo")
public class DemoController {

    @Value("${test.config}")
    private String testConfig;

    @Value("${test2.config2}")
    private String testConfig2;

    @Value("${test3.config3}")
    private String testConfig3;

    @RequestMapping("/test1")
    public String test1(){
        System.out.println("动态刷新配置文件："+testConfig);
        System.out.println("同一微服务动态刷新共享配置文件："+testConfig2);
        System.out.println("不同微服务动态刷新共享配置文件："+testConfig3);
        return "hello。。。";
    }

}
