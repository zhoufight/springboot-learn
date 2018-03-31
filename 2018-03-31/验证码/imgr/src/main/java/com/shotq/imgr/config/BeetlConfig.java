package com.shotq.imgr.config;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeetlConfig {
	
	@Value("${spring.beetl.prefix}") String templatePath;
	
	@Bean(initMethod = "init")
	public BeetlGroupUtilConfiguration beetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlConfiguration = new BeetlGroupUtilConfiguration();
		//beetl中可以自定义一些方法
		//GroupTemplate groupTemplate = beetlConfiguration.getGroupTemplate();
		//groupTemplate.registerFunction("", new Function());
		//前缀
		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(),templatePath);
		beetlConfiguration.setResourceLoader(resourceLoader);
		//设置配置
		//beetlConfiguration.setConfigProperties(configProperties);
		return beetlConfiguration;
	}
	
	@Bean
	public BeetlSpringViewResolver beetlSpringViewResolver() {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration());
		//后缀
		beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        return beetlSpringViewResolver;
	}

}
