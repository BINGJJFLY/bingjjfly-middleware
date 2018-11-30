package com.wjz.message;

import java.util.Collection;

import com.wjz.converter.Converter;
import com.wjz.crypto.Cipher;
import com.wjz.crypto.NegativeCipher;
import com.wjz.logger.LogService;
import com.wjz.logger.Slf4jLogService;

public class DefaultMessageManager<T> implements MessageManager<T> {

	private final Converter<T> converter;
	private LogService logService;
	private Cipher cipher;

	public DefaultMessageManager(Converter<T> converter) {
		this.converter = converter;
		logService = new Slf4jLogService();
		cipher = new NegativeCipher();
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setCipher(Cipher cipher) {
		this.cipher = cipher;
	}

	@Override
	public T string2Object(String ciphertext) throws MessageException {
		String plaintext = null;
		try {
			// ==> 密文转明文
			plaintext = cipher.decode(ciphertext);
			// ==> 日志记录明文
			logService.log(plaintext);
			// ==> 明文转DTO对象
			return converter.stringConvert2Object(plaintext);
		} catch (Exception e) {
			logService.error("【字符串转换为DTO对象时异常】：密文信息[" + ciphertext + "]，明文信息[" + plaintext + "]，转换类型["
					+ converter.rawType() + "]", e);
			throw new MessageException(e);
		}
	}

	@Override
	public Collection<T> string2Collection(String ciphertext) throws MessageException {
		String plaintext = null;
		try {
			// ==> 密文转明文
			plaintext = cipher.decode(ciphertext);
			// ==> 日志记录明文
			logService.log(plaintext);
			// ==> 明文转DTO对象集合
			return converter.stringConvert2Collection(plaintext);
		} catch (Exception e) {
			logService.error("【字符串转换为DTO对象集合时异常】：密文信息[" + ciphertext + "]，明文信息[" + plaintext + "]，转换类型["
					+ converter.rawType() + "]", e);
			throw new MessageException(e);
		}
	}

	@Override
	public String object2String(T dto) {
		String plaintext = null;
		try {
			// ==> DTO对象转密文
			plaintext = converter.objectConvert2String(dto);
			// ==> 日志记录明文
			logService.log(plaintext);
			// ==> 明文转密文
			return cipher.encrypt(plaintext);
		} catch (Exception e) {
			logService.error("【DTO对象转换为字符串时异常】：DTO对象[" + dto + "]，明文信息[" + plaintext + "]，转换类型["
					+ converter.rawType() + "]", e);
			throw new MessageException(e);
		}
	}

	@Override
	public String collection2String(Collection<T> dtos) {
		String plaintext = null;
		try {
			// ==> DTO对象集合转密文
			plaintext = converter.collectionConvert2String(dtos);
			// ==> 日志记录明文
			logService.log(plaintext);
			// ==> 明文转密文
			return cipher.encrypt(plaintext);
		} catch (Exception e) {
			logService.error("【DTO对象集合转换为字符串时异常】：DTO对象集合[" + dtos + "]，明文信息[" + plaintext + "]，转换类型["
					+ converter.rawType() + "]", e);
			throw new MessageException(e);
		}
	}

}
