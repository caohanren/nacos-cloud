package com.study.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.model.user.entity.AppUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AppUserDao extends BaseMapper<AppUserEntity> {

	@Select("select * from app_user t where t.id = #{id}")
	AppUserEntity findById(Long id);

}
