package com.chw.spb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chw.spb.system.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chw.spb.system.dao.PermissionMapper;
import com.chw.spb.system.dao.UserMapper;
import com.chw.spb.system.entity.Permission;
import com.chw.spb.system.entity.User;
import com.chw.spb.system.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionTest {
	
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private PermissionService permissionService;
	
	

	@Test
	public void contextLoads() {
		List<Permission> currentUserPermission = permissionMapper.getCurrentUserPermission("1");
		System.out.println("zhiqian "+currentUserPermission.size());
		Set<Permission> set = new HashSet<>(currentUserPermission);
		System.out.println("zhihou "+set.size());
		for (Permission permission : set) {

		}
	}

	@Test
	public void test() {
		Set<Permission> currentUserPermission = permissionService.getCurrentUserPermission("1");
		System.out.println("zhiqian "+currentUserPermission.size());


	}


}
