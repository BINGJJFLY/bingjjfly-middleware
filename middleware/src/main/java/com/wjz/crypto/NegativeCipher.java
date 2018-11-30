package com.wjz.crypto;

/**
 * 不作为的加密/解密者
 * 
 * @author iss002
 *
 */
public class NegativeCipher implements Cipher {

	@Override
	public String encrypt(String plaintext) {
		return plaintext;
	}

	@Override
	public String decode(String ciphertext) {
		return ciphertext;
	}

}
