package com.chw.spb.system.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chw.spb.system.entity.Permission;

public interface PermissionMapper {
	
    int insert(Permission record);

    int insertSelective(Permission record);
    
    List<Permission> getCurrentUserPermission(@Param("id")String id);

    List<Permission> listAll();
}