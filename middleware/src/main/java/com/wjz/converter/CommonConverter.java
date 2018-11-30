package com.wjz.converter;

import org.apache.ibatis.type.TypeReference;

public abstract class CommonConverter<T> extends TypeReference<T> implements Converter<T> {
	
	@SuppressWarnings("unchecked")
	protected Class<T> rawType() {
		return (Class<T>) getRawType();
	}

}
