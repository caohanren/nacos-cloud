package com.study.oauth.service;

import com.study.oauth.util.AuthToken;

public interface LoginService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret,String grantType);
}

