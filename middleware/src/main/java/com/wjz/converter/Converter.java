package com.wjz.converter;

import java.util.Collection;

/**
 * 转换器
 * 
 * @author iss002
 *
 */
public interface Converter<T> {

	/**
	 * 字符串转换为DTO对象
	 * 
	 * @param plaintext
	 * @return
	 * @throws Exception
	 */
	T stringConvert2Object(String plaintext) throws Exception;

	/**
	 * 字符串转换为DTO对象集合
	 * 
	 * @param plaintext
	 * @return
	 * @throws Exception
	 */
	Collection<T> stringConvert2Collection(String plaintext) throws Exception;

	/**
	 * DTO对象转换为字符串
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	String objectConvert2String(T dto) throws Exception;

	/**
	 * DTO对象集合转换为字符串
	 * 
	 * @param dtos
	 * @return
	 * @throws Exception
	 */
	String collectionConvert2String(Collection<T> dtos) throws Exception;

	/**
	 * 转换的类型
	 * 
	 * @return
	 */
	Class<T> rawType();
}
