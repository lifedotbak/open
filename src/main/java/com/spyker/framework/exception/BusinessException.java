package com.spyker.framework.exception;

/**
 * 业务异常
 *
 * @author spyker
 */
public final class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	private Integer code;

	/**
	 * 错误提示
	 */
	private String message;

	/**
	 * 错误明细，内部调试错误
	 * <p>
	 */
	private String detailMessage;

	/**
	 * 空构造方法，避免反序列化问题
	 */
	public BusinessException() {
	}

	public BusinessException(String message) {
		this.message = message;
	}

	public BusinessException(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public BusinessException setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
		return this;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public BusinessException setMessage(String message) {
		this.message = message;
		return this;
	}

	public Integer getCode() {
		return code;
	}
}