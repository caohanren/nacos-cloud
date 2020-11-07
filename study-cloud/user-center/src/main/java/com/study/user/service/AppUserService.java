package com.study.user.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.model.user.UserJwt;
import com.study.model.user.entity.AppUserEntity;

public interface AppUserService extends IService<AppUserEntity> {

	UserJwt findByUsername(String username);
}
