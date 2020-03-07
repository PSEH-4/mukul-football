package io.test.football.exception.util;

public enum ErrorMessages {



	DATA_NOT_FOUND( "001", "data not found");

	private final String errorMessage; 
	private final String errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	private ErrorMessages(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMessage = errorMsg;
	}
}
