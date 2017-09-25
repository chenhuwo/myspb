package com.chw.spb.system.service;

import com.chw.spb.common.EasyPage;
import com.chw.spb.system.dao.UserMapper;
import com.chw.spb.system.entity.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	public User findUserByUsernameAndPwd(String username,String pwd) {
		return userMapper.findUserByUsernameAndPwd(username, pwd);
	}
	
	public User findUserByUsername(String username){
		return userMapper.findUserByUsername(username);
	}
	
	public List<User> listUser(){
		return userMapper.listUser();
	}

	public List<User> listUser(EasyPage page){
		return userMapper.listUserByPage(page);
	}

	public int save(User user) {
		Md5Hash md5Hash = new Md5Hash(user.getPlainPassword());
		user.setPassword(md5Hash.toHex());
		user.setCreateDate(new Date());
		return userMapper.insert(user);
	}

	public int update(User user) {
		return userMapper.updateByUserIdSelective(user);
	}

	public int deleteById(String id) {
		return userMapper.deleteUserById(id);
	}

	/**
	 * 根据用户id获取对应角色的id集合
	 * @param userId
	 * @return
	 */

	public List<Integer> listUserRoleIdByUserId(String userId){
		return userMapper.listUserRoleByUserId(userId);
	}

	/**
	 * 根据用户id获取用户
	 * @return
	 */
	@Cacheable(value = "users" ,key = "#userId")
	public User findUserByUserId(String userId) {
		return userMapper.findUserByUserId(userId);
	}

}
