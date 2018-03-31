package com.shotq.imgr.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})    //注解的目标
@Retention(RetentionPolicy.RUNTIME)	//注解执行的时间，这里是运行的时候x
public @interface LogAnno {
	
}
