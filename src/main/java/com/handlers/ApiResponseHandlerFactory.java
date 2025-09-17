package com.handlers;

public class ApiResponseHandlerFactory {
	public static ApiResponseHandler getApiClient(String type) {
		switch(type) {
		case "OKTA":
			return new OktaApiHandler();
		case "POSTMAN":
			return new PostManApiHandler();
		default:
			throw new IllegalArgumentException("Unknown API type:" + type);
		}
	}
}
