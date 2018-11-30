package com.wjz.message;

import java.util.Collection;

/**
 * 信息管理者
 * 
 * @author iss002
 *
 */
public interface MessageManager<T> {
	
	/**
	 * 加密的字符串转换为DTO对象
	 * 
	 * <ul>
	 * <li>密文解密</li>
	 * <li>记录日志</li>
	 * <li>字符串转换为DTO对象</li>
	 * </ul>
	 * 
	 * @param ciphertext
	 * @return
	 */
	T string2Object(String ciphertext) throws MessageException;
	
	/**
	 * 加密的字符串转换为DTO对象集合
	 * 
	 * <ul>
	 * <li>密文解密</li>
	 * <li>记录日志</li>
	 * <li>字符串转换为DTO对象集合</li>
	 * </ul>
	 * 
	 * @param ciphertext
	 * @return
	 */
	Collection<T> string2Collection(String ciphertext);

	/**
	 * DTO对象转换为加密的字符串
	 * 
	 * <ul>
	 * <li>DTO对象转换为字符串</li>
	 * <li>记录日志</li>
	 * <li>明文加密</li>
	 * </ul>
	 * 
	 * @param dto
	 * @return
	 */
	String object2String(T dto);
	
	/**
	 * DTO对象集合转换为加密的字符串
	 * 
	 * <ul>
	 * <li>DTO对象集合转换为字符串</li>
	 * <li>记录日志</li>
	 * <li>明文加密</li>
	 * </ul>
	 * 
	 * @param dtos
	 * @return
	 */
	String collection2String(Collection<T> dtos);

}
