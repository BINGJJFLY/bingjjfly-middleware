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
		final ResolverUtil<Converter<T>> resolver = new ResolverUtil<>();
		Set<Class<? extends Converter<T>>> converterTypes = resolver.findAnnotated(Convertor.class, packageName)
				.getClasses();
		if (!CollectionUtils.isEmpty(converterTypes)) {
			for (Class<? extends Converter<T>> converterType : converterTypes) {
				if (!converterType.isAnonymousClass() && !converterType.isInterface()
						&& !Modifier.isAbstract(converterType.getModifiers())) {
					register(converterType);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void register(Class<? extends Converter<T>> converterType) {
		if (!existing(converterType)) {
			Convertor convertorAnno = converterType.getAnnotation(Convertor.class);
			Class<T> target = (Class<T>) convertorAnno.target();
			register(target, newInstance(converterType));
		}
	}

	public void register(Class<T> target, Converter<T> instance) {
		CONVERTOER_MAP.put(target, instance);
	}

	public Converter<T> getConverter(Class<T> target) {
		return CONVERTOER_MAP.get(target);
	}

	private Converter<T> newInstance(Class<? extends Converter<T>> converterType) {
		try {
			Constructor<? extends Converter<T>> constructor = converterType.getConstructor();
			return constructor.newInstance();
		} catch (Exception e) {
			throw new RegisterException("an exception occurred during initialization", e);
		}
	}

	private boolean existing(Class<? extends Converter<T>> converterType) {
		return CONVERTOER_MAP.containsKey(converterType);
	}

	public static class RegisterException extends RuntimeException {

		private static final long serialVersionUID = 2108705720088926059L;

		public RegisterException(String message, Throwable cause) {
			super(message, cause);
		}

	}
}
