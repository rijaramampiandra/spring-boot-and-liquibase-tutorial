package com.java.spring.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

	private DateUtils() {
		super();
	}

	/**
	 * Format date.
	 * 
	 * @param date
	 *            the date.
	 * @param pattern
	 *            the pattern.
	 * @return the date formatted.
	 */
	public static String format(Date date, String pattern) {
		String dateFormat = "dd/MM/yyyy";
		if (date == null) {
			return StringUtils.EMPTY;
		}

		if (!StringUtils.isEmpty(pattern)) {
			dateFormat = pattern;
		}

		final SimpleDateFormat formater = new SimpleDateFormat(dateFormat);

		return formater.format(date);
	}
}
