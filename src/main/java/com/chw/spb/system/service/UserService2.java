package com.chw.spb.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
@Service
public class UserService2 {
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("secondJdbcTemplate")
	JdbcTemplate jdbcTemplate2;
	
	public Object listUser(){
		List<Map<String, Object>> List = jdbcTemplate.queryForList("select * from user");
		return List;
	}
	
	
	public Object listSnakerUser(){
		List<Map<String, Object>> List = jdbcTemplate2.queryForList("select * from sec_user");
		return List;
	}
}
