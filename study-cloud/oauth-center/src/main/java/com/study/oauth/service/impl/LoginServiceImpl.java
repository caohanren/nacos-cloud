package com.study.oauth.service.impl;

import com.study.oauth.service.LoginService;
import com.study.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou.oauth.service.impl *
 * @since 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret, String grandType) {

        //1.定义url (申请令牌的url)
        //参数 : 微服务的名称spring.appplication指定的名称
        ServiceInstance serviceInstance = loadBalancerClient.choose("oauth-center");
        if (serviceInstance == null) {
            throw new RuntimeException("找不到对应的服务");
        }
        //String url =choose.getUri().toString()+"/oauth/token";
        //http://localhost:8666/user/login?username=zhangsan&password=scret
        String path ="http://127.0.0.1:8666/oauth/token";
        System.out.println("请求令牌的url:"+path);

        //2.定义头信息 (有client id 和client secr)
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization","Basic "+Base64.getEncoder().encodeToString(new String(clientId+":"+clientSecret).getBytes()));

        //3. 定义请求体  有授权模式 用户的名称 和密码
        MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type",grandType);
        formData.add("username",username);
        formData.add("password",password);

        //4.模拟浏览器 发送POST 请求 携带 头 和请求体 到认证服务器
        HttpEntity<MultiValueMap> requestentity = new HttpEntity<MultiValueMap>(formData,headers);

        //指定 restTemplate当遇到400或401响应时候也不要抛出异常，也要正常返回值
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                //当响应的值为400或401时候也要正常响应，不要抛出异常
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });
        Map map = null;
        try {
            //http请求spring security的申请令牌接口
            //参数1  指定要发送的请求的url,参数3 指定请求实体(包含头和请求体数据)
            ResponseEntity<Map> mapResponseEntity = restTemplate.exchange(path, HttpMethod.POST,requestentity, Map.class);
            //获取响应数据
            map = mapResponseEntity.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
        if(map == null || map.get("access_token") == null
                || map.get("refresh_token") == null
                || map.get("jti") == null) {
            //jti是jwt令牌的唯一标识作为用户身份令牌
            throw new RuntimeException("创建令牌失败！");
        }

        //封装
        AuthToken authToken = new AuthToken();
        //访问令牌(jwt)
        String accessToken = (String) map.get("access_token");
        //刷新令牌(jwt)
        String refreshToken = (String) map.get("refresh_token");
        //jti，作为用户的身份标识
        String jwtToken= (String) map.get("jti");

        authToken.setJti(jwtToken);
        authToken.setAccessToken(accessToken);
        authToken.setRefreshToken(refreshToken);

        //6.返回
        return authToken;
    }


    public static void main(String[] args) {
        byte[] decode = Base64.getDecoder().decode(new String("Y2hhbmdnb3UxOmNoYW5nZ291Mg==").getBytes());
        System.out.println(new String(decode));
    }

}
