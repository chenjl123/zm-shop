package com.zm.order.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusLog {
	//来源
	String op_from() default "order";
	
	//类型
	String op_type()default "out"; 
}
