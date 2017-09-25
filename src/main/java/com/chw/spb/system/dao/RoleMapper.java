package com.chw.spb.system.dao;

import com.chw.spb.common.EasyPage;
import com.chw.spb.system.entity.Permission;
import com.chw.spb.system.entity.Role;

import java.util.List;

public interface RoleMapper {

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> listRole();

    List<Role> listRoleByPage(EasyPage easyPage);

    List<Permission> listPermissionByRoleId(Integer id);



}