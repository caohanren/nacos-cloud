package com.study.oauth.feign;

import com.study.model.user.UserJwt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FeignClient 是一个请求的feign调用客户端
 * value 表示请求的相关的server_id 暴露到eureka里面的
 * path  是请求的相关路径
 * contextId 表示相关的feign client模块
 * */
@FeignClient(
        value ="user-center",
        path = "/user",
        contextId = "userClient",
        fallback = UserClient.DefaultFallback.class
)
public interface UserClient {
    @GetMapping(value = "/findByUsername")
    UserJwt findByUsername(@RequestParam("username")String username);

    /**
     * 容错处理类，当调用失败时，简单返回空字符串
     */
    @Component
    class DefaultFallback implements UserClient {

        @Override
        public UserJwt findByUsername(String username) {
            System.out.println("报错了。。。。");
            return null;
        }
    }

}
