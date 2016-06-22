package com.landcoder.common.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * 非数字参数异常
 * @author landcoder
 * @company oschina
 */
@SuppressWarnings("serial")
public class MustNumberException extends TemplateModelException {
	public MustNumberException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a number.");
	}
}
