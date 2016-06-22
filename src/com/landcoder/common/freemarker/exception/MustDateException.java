package com.landcoder.common.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * 非日期参数异常
 * @author landcoder
 * @company oschina
 */
@SuppressWarnings("serial")
public class MustDateException extends TemplateModelException {
	public MustDateException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a date.");
	}
}
