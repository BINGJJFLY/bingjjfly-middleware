package com.wjz.converter;

import java.util.Collection;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonConverter<T> extends CommonConverter<T> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public T convert2Object(String plaintext) throws Exception {
		return objectMapper.readValue(plaintext, rawType());
	}

	@Override
	public Collection<T> convert2Collection(String plaintext) throws Exception {
		JavaType javaType = getJavaType(Collection.class, rawType());
		return objectMapper.readValue(plaintext, javaType);
	}

	@Override
	public String convert2String(T dto) throws Exception {
		return objectMapper.writeValueAsString(dto);
	}

	@Override
	public String convert2String(Collection<T> dtos) throws Exception {
		return objectMapper.writeValueAsString(dtos);
	}
	
	private JavaType getJavaType(Class<?> clazz, Class<T> rawType) {
		return objectMapper.getTypeFactory().constructParametricType(clazz, rawType);
	}

}
