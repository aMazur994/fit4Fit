package com.amaz.trainingassistantbackend.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class RestBaseException extends RuntimeException{
	private String msgKey;
	private HttpStatus status;
	private String msgParams;
	public static RestBaseException notFound(String message, String errMsgKey) {
		return new RestBaseException(message, HttpStatus.NOT_FOUND , errMsgKey);
	}
}
