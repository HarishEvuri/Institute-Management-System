package com.harish.institutemanagement.utils;

import java.sql.Date;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public final class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return Date.valueOf(source);
	}

}