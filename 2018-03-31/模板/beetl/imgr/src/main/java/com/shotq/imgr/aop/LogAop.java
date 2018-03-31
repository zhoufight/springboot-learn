package com.shotq.imgr.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	//切点
	@Pointcut(value = "@annotation(com.shotq.imgr.aop.LogAnno)")
	public void service() {}
	
	@Around("service()")
	public void runMathod(ProceedingJoinPoint point) throws Throwable {
		Object result = point.proceed(); 	//业务代码执行
		MethodSignature ms = (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		log.info("执行方法："+method.getName());
	}
}
