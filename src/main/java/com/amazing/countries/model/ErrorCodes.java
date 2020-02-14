package com.amazing.countries.model;

public class ErrorCodes {

	private Integer errorCode;
	private String statusMessage;

	public ErrorCodes(Integer errorCode, String statusMessage) {

		this.errorCode = errorCode;
		this.statusMessage = statusMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
