package com.landcoder.common.web;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateTypeEditor extends PropertyEditorSupport {
	public static final DateFormat DF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 短类型日期长度
	 */
	public static final int SHORT_DATE = 10;

	public static final int LONG_DATE = 19;

	public void setAsText(String text) throws IllegalArgumentException {
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		try {
			if (text.length() <= SHORT_DATE) {
				setValue(DF_SHORT.parse(text));
			} else if (text.length() <= LONG_DATE) {
				setValue(DF_LONG.parse(text));
			}
		} catch (ParseException ex) {
			IllegalArgumentException iae = new IllegalArgumentException(
					"Could not parse date: " + ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DF_LONG.format(value) : "");
	}
}
