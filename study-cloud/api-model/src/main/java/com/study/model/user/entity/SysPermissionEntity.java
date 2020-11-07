package com.study.model.user.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 权限标识
 */
@Data
public class SysPermissionEntity implements Serializable {

    private static final long serialVersionUID = 280565233032255804L;

    private Long id;
    private Long parentId;
    private String url;
    private String permission;
    private String name;
    private Integer type;
    private Integer orderNum;
    private Date createTime;
    private Date updateTime;

}
