package com.wjz.annotation;

/**
 * 转换器注解
 * 
 * @author iss002
 *
 */
public @interface Convertor {
	
	/**
	 * 目标转换类
	 * 
	 * @return
	 */
	Class<?> target();

}
