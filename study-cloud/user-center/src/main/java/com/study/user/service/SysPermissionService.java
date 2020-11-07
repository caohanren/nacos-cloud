package com.study.user.service;

import com.study.model.user.entity.SysPermissionEntity;

import java.util.Set;

public interface SysPermissionService {

	/**
	 * 根绝角色ids获取权限集合
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<SysPermissionEntity> findByRoleIds(Set<Long> roleIds);


}
