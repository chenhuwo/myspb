package com.chw.spb.system.service;

import com.chw.spb.common.EasyPage;
import com.chw.spb.system.dao.RoleMapper;
import com.chw.spb.system.dao.RolePermissionMapper;
import com.chw.spb.system.dao.UserMapper;
import com.chw.spb.system.entity.Permission;
import com.chw.spb.system.entity.Role;
import com.chw.spb.system.entity.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	public List<Role> listRole(){
		return roleMapper.listRole();
	}

	public List<Role> listRole(EasyPage page) {
		return	roleMapper.listRoleByPage(page);
	}

	public List<Permission> listPermissionByRoleId(Integer roleId) {
		return roleMapper.listPermissionByRoleId(roleId);
	}

	@Transactional
	public void updateRolePermission(Integer roleId, List<Integer> permIds) {
		if (!StringUtils.isEmpty(roleId)) {
			rolePermissionMapper.deletePermissonByRoleId(roleId);
		}
		rolePermissionMapper.addPermissionByRoleId(roleId,permIds);
	}
}
