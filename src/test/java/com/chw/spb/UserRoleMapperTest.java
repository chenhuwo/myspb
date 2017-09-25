package com.chw.spb;

import com.chw.spb.system.dao.UserMapper;
import com.chw.spb.system.dao.UserRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleMapperTest {
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	

	@Test
	public void contextLoads() {
		Integer[] arr = new Integer[]{55,56,77,88,99,1112};
		List<Integer> integers = Arrays.asList(arr);
		userRoleMapper.addUserRoleByUserId("111",integers);
	}

	@Test
	public void deleteUserROle() {
		userRoleMapper.deleteUserRoleByUserId("111");
	}




}
