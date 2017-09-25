package com.chw.spb.system.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chw.spb.system.dao.PermissionMapper;
import com.chw.spb.system.entity.Permission;

/**
 * 
 * @author chw
 *
 */
@Service
public class PermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	public Set<Permission> getCurrentUserPermission(String id){
		//
		TreeSet<Permission> treeSet = new TreeSet<>(new Comparator<Permission>() {
			@Override
			public int compare(Permission o1, Permission o2) {
				if (o1.getSort() == null || o2.getSort() == null) {
					return o1.getId() - o2.getId();
				}
				if (o1.getSort() > o2.getSort()) {
					return 1;
				} else if (o1.getSort() < o2.getSort()) {
					return -1;
				}else {
					return o1.getId() - o2.getId();
				}
			}
		});
		treeSet.addAll(permissionMapper.getCurrentUserPermission(id));
		return treeSet;
	}

	public List<Permission> listAll() {
		return permissionMapper.listAll();
	}
}
