package com.spyker.framework.exception.handler;

public class ExceptionLogUsableHandler {

	private ExceptionLogHandler exceptionLogHandler;

	public void initHandler(ExceptionLogHandler exceptionLogHandler) {

		this.exceptionLogHandler = exceptionLogHandler;
	}

	public ExceptionLogHandler getHandler() {
		return exceptionLogHandler;
	}
}