package com.chw.spb;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class PasswordTest {
	
	@Test
	public void test(){
		
		Md5Hash md5Hash = new Md5Hash("admin");
		String hex = md5Hash.toHex();
		System.out.println(hex);
	}
}
