package com.chw.spb;

import com.chw.spb.system.entity.User;
import com.chw.spb.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SpbApplicationTests {
	
	@Autowired
	private UserService userService;
	
	

	@Test
	public void contextLoads() {
		User user = userService.findUserByUsernameAndPwd("admin", "159ae5f48f14e89f3f9f54dc995f1f276d472b54");
		System.out.println("1111111111111111111111111111");
		System.out.println(user.getLoginName());
	}

	@Test
	public void testDeleteUser() {
		userService.deleteById(8 +"");
	}
	

}
