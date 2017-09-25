package com.chw.spb;

import com.chw.spb.system.dao.UserMapper;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserMapper usermapper;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	

	@Test
	public void contextLoads() {
		usermapper.deleteUserById(9+"");
	}
	@Test
	public void listUserRole() {
		List<Integer> list = usermapper.listUserRoleByUserId(1 + "");
		System.out.println(list);
	}


	@Test
	public void getSqlsession() {
		Configuration configuration = sqlSessionFactory.getConfiguration();
		Collection<ResultMap> resultMaps = configuration.getResultMaps();
		ResultMap resultMap = configuration.getResultMap("com.chw.spb.system.dao.UserMapper.BaseResultMap");
		List<ResultMapping> resultMappings = resultMap.getResultMappings();
		for (ResultMapping resultMapping : resultMappings) {
			System.out.println(resultMapping.getColumn());
			System.out.println(resultMapping.getProperty());
		}


	}


}
