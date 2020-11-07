package com.study.user.dao;

import com.study.model.user.entity.SysPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 角色权限关系<br>
 * 角色和权限是多对多关系，sys_role_permission是中间表
 *
 */
@Mapper
public interface RolePermissionDao {

	Set<SysPermissionEntity> findPermissionsByRoleIds(@Param("roleIds") Set<Long> roleIds);

}
