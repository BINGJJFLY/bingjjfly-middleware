package com.wjz.crypto;

/**
 * 加密/解密者
 * 
 * @author iss002
 *
 */
public interface Cipher {

	/**
	 * 字符串加密
	 * 
	 * @param plaintext
	 * @return
	 */
	String encrypt(String plaintext);
	
	/**
	 * 字符串解密
	 * 
	 * @param ciphertext
	 * @return
	 */
	String decode(String ciphertext);
	
}
