package com.chw.spb;

import com.chw.spb.system.dao.RoleMapper;
import com.chw.spb.system.dao.UserMapper;
import com.chw.spb.system.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {
	
	@Autowired
	private RoleMapper roleMapper;
	
	

	@Test
	public void listRole() {
		List<Role> roles = roleMapper.listRole();
		for (Role role : roles) {
			System.out.println(role);
		}
	}




}
