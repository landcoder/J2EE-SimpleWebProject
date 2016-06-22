package com.landcoder.common.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * 非字符串参数异常
 * @author landcoder
 * @company oschina
 */
@SuppressWarnings("serial")
public class MustStringException extends TemplateModelException {
	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}
}
