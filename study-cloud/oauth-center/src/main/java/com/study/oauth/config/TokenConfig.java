package com.study.oauth.config;
import java.security.KeyPair;
import javax.annotation.Resource;

import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class TokenConfig {

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    @Resource
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;

    @Bean({"keyProp"})
    public KeyProperties keyProperties() {
        return new KeyProperties();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.jwtAccessTokenConverter());
    }

    @Bean(name = {"oauth2JwtAccessTokenConverter"})
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        System.out.println("校验密码库");
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        //公钥私钥加签验签
        KeyPair keyPair = (new KeyStoreKeyFactory(
                this.keyProperties.getKeyStore().getLocation(),
                this.keyProperties.getKeyStore().getSecret().toCharArray()))
                .getKeyPair(
                        this.keyProperties.getKeyStore().getAlias(),
                        this.keyProperties.getKeyStore().getPassword().toCharArray()
                );
        converter.setKeyPair(keyPair);
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter)converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(this.customUserAuthenticationConverter);
        return converter;
    }
}
