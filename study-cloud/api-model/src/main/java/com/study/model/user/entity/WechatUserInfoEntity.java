package com.study.model.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信个人用户信息
 */
@Getter
@Setter
@ToString
public class WechatUserInfoEntity implements Serializable {

    private static final long serialVersionUID = 6750304307961875043L;

    private Long id;
    private String openid;
    private String unionid;
    private Long userId;
    private String app;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private Date createTime;
    private Date updateTime;
}
