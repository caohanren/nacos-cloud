package com.study.user.service.impl;

import com.study.model.user.entity.SysPermissionEntity;
import com.study.user.dao.RolePermissionDao;
import com.study.user.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Slf4j
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

	@Resource
	private RolePermissionDao rolePermissionDao;

	@Override
	public Set<SysPermissionEntity> findByRoleIds(Set<Long> roleIds) {
		return rolePermissionDao.findPermissionsByRoleIds(roleIds);
	}

}
