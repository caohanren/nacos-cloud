package com.study.oauth.interceptor;

import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    private String createToken(){

        //公钥私钥加签验签
        KeyPair keyPair = (new KeyStoreKeyFactory(
                this.keyProperties.getKeyStore().getLocation(),
                this.keyProperties.getKeyStore().getSecret().toCharArray()))
                .getKeyPair(
                        this.keyProperties.getKeyStore().getAlias(),
                        this.keyProperties.getKeyStore().getPassword().toCharArray()
                );

        //获取私钥
        RSAPrivateKey rsaPrivate = (RSAPrivateKey) keyPair.getPrivate();

        //定义Payload
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("username", "caohanren");
        tokenMap.put("authorities", new String[]{"/user/findByUsername"});

        //生成Jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(rsaPrivate));

        //取出令牌
        String encoded = jwt.getEncoded();
        //获取令牌
        return encoded;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = createToken();
        System.out.println("获取到管理员token。。。"+token);
        requestTemplate.header("Authorization","Bearer "+token);
    }


}