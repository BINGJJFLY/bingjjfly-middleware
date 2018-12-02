package com.wjz.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 转换器注解
 * 
 * @author iss002
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Convertor {
	
	/**
	 * 目标转换类
	 * 
	 * @return
	 */
	Class<?> target();

}
