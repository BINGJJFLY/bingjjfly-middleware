package com.wjz.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.io.ResolverUtil;
import org.springframework.util.CollectionUtils;

import com.wjz.annotation.Convertor;

/**
 * 转换器注册机
 * 
 * @author iss002
 *
 */
public final class ConverterRegistry<T> {

	private final Map<Class<T>, Converter<T>> CONVERTOER_MAP = new ConcurrentHashMap<>();

	public ConverterRegistry(String packageName) {
		register(packageName);
	}

	public void register(String packageName) {
		final ResolverUtil<Class<T>> resolver = new ResolverUtil<>();
		Set<Class<? extends Class<T>>> converterTypes = resolver.findAnnotated(Convertor.class, packageName)
				.getClasses();
		if (!CollectionUtils.isEmpty(converterTypes)) {
			for (Class<? extends Class<T>> converterType : converterTypes) {
				if (!converterType.isAnonymousClass() && !converterType.isInterface()
						&& !Modifier.isAbstract(converterType.getModifiers())) {
					register(converterType);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void register(Class<? extends Class<T>> converterType) {
		if (!existing(converterType)) {
			Convertor convertorAnno = converterType.getAnnotation(Convertor.class);
			Class<T> target = (Class<T>) convertorAnno.target();
			register(target, (Converter<T>) newInstance(target));
		}
	}

	public void register(Class<T> target, Converter<T> instance) {
		CONVERTOER_MAP.put(target, instance);
	}
	
	public Converter<T> getConverter(Class<T> target) {
		return CONVERTOER_MAP.get(target);
	}

	private T newInstance(Class<T> target) {
		try {
			Constructor<T> constructor = target.getConstructor();
			return (T) constructor.newInstance();
		} catch (Exception e) {
			throw new RegisterException("an exception occurred during initialization", e);
		}
	}

	private boolean existing(Class<? extends Class<T>> converterType) {
		return CONVERTOER_MAP.containsKey(converterType);
	}
	
	public static class RegisterException extends RuntimeException {

		private static final long serialVersionUID = 2108705720088926059L;
		
		public RegisterException(String message, Throwable cause) {
			super(message, cause);
		}
		
	}
}
