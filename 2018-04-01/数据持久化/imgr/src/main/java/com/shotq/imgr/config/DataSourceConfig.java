package com.shotq.imgr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DataSourceConfig {
	
	@Bean
	public ServletRegistrationBean datasourceServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(new StatViewServlet());
		registrationBean.addUrlMappings("/druid/*");
		registrationBean.addInitParameter("allow", "127.0.0.1");  
        registrationBean.addInitParameter("deny", "192.168.31.234"); 
        //登录页面的用户名和密码
        registrationBean.addInitParameter("loginUsername", "admin");  
        registrationBean.addInitParameter("loginPassword", "123456");
        //是否可以重置
        registrationBean.addInitParameter("resetEnable", "false");  
		return registrationBean;
	} 
	
	@Bean
	public FilterRegistrationBean datasourceFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean ();
		registrationBean.setFilter(new WebStatFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.addInitParameter("exclusions", "*.js, *.gif, *.jpg, *.png, *.css, *.ico, /druid/*, /login");
		return registrationBean;
	}
	
	public DataSource dataSource(
			@Value("${spring.datasource.driver-class-name}") String driver,
			@Value("${spring.datasource.url}") String url,
			@Value("${spring.datasource.username}") String userName,
			@Value("${spring.datasource.password}") String password,
			@Value("${spring.datasource.maxActive}") int maxActive,
			@Value("${spring.datasource.filters}") String filters
		) {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driver);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(userName);
		druidDataSource.setPassword(password);
		druidDataSource.setMaxActive(maxActive);
		try {
			druidDataSource.setFilters(filters);
		}catch(Exception e) {
			
		}
		return druidDataSource;
	}

}
