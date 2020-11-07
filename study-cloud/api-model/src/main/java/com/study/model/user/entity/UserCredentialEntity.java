package com.study.model.user.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户账号类型
 *
 * @author 小威老师
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialEntity implements Serializable {

    private static final long serialVersionUID = -958701617717204974L;

    private String username;
    private String type;
    private Long userId;

}
