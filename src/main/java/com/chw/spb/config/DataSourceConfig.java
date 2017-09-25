package com.chw.spb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 
 * @author chw
 *
 */
@Configuration
public class DataSourceConfig {

	@Bean("primaryDataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource primaryDataSource(){
		return DataSourceBuilder.create().build();
	}
	@Bean("secondDataSource")
	@ConfigurationProperties(prefix="spring.second_datasource")
	public DataSource secondDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
}
