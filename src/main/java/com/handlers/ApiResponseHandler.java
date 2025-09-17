package com.handlers;

import java.io.IOException;
import java.util.List;

import com.models.ApiUsers;

public interface ApiResponseHandler {
	public List<ApiUsers> getUsersList(String url, String apiToken) throws IOException;
}
