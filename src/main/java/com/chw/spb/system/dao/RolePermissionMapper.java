package com.chw.spb.system.dao;

import com.chw.spb.system.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    void deletePermissonByRoleId(Integer roleId);

    void addPermissionByRoleId(@Param("roleId") Integer roleId,@Param("permissionIds") List<Integer> list);

}