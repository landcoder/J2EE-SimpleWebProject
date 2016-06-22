package com.landcoder.framework.exception;

/**
 * 回滚异常类
 * @author landcoder
 * @company oschina
 */
@SuppressWarnings("serial")
public class RollbackException extends Exception {

	public RollbackException() {
		super();
	}

	public RollbackException(String msg, Throwable e) {
		super(msg, e);
	}

	public RollbackException(String msg) {
		super(msg);
	}

	public RollbackException(Throwable e) {
		super(e);
	}

}
