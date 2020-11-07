package com.study.user.service.impl;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.model.user.UserJwt;
import com.study.model.user.entity.AppUserEntity;
import com.study.model.user.entity.SysPermissionEntity;
import com.study.model.user.SysRoleEntity;
import com.study.user.dao.AppUserDao;
import com.study.user.dao.UserCredentialsDao;
import com.study.user.dao.UserRoleDao;
import com.study.user.service.AppUserService;
import com.study.user.service.SysPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserDao, AppUserEntity> implements AppUserService {

    @Resource
    private AppUserDao appUserDao;

    @Resource
    private UserCredentialsDao userCredentialsDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private SysPermissionService sysPermissionService;



    @Transactional
    @Override
    public UserJwt findByUsername(String username) {
        //QueryWrapper<AppUserEntity> wrapper = new QueryWrapper<AppUserEntity>().eq("", "");
        //wrapper.select("1字段","2字段");
        //baseMapper.selectList();
        //baseMapper.selectOne(new QueryWrapper<AppUserEntity>());

        AppUserEntity appUser = userCredentialsDao.findUserByUsername(username);
        if (appUser != null) {
            UserJwt loginAppUser = new UserJwt();
            BeanUtils.copyProperties(appUser, loginAppUser);

            Set<SysRoleEntity> sysRoles = userRoleDao.findRolesByUserId(appUser.getId());
            loginAppUser.setSysRoles(sysRoles);// 设置角色

            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<Long> roleIds = sysRoles.parallelStream().map(SysRoleEntity::getId).collect(Collectors.toSet());
                Set<SysPermissionEntity> sysPermissions = sysPermissionService.findByRoleIds(roleIds);
                if (!CollectionUtils.isEmpty(sysPermissions)) {
                    Set<String> permissions = sysPermissions.parallelStream().map(SysPermissionEntity::getPermission)
                            .collect(Collectors.toSet());

                    loginAppUser.setPermissions(permissions);// 设置权限集合
                }

            }

            return loginAppUser;
        }

        return null;
    }


}
