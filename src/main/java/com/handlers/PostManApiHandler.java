package com.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.models.ApiUsers;

public class PostManApiHandler implements ApiResponseHandler {
	public List<ApiUsers> getUsersList(String url, String token) throws IOException{
		List<ApiUsers> usersList = new ArrayList<>();
		URL apiUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		String response = sb.toString();
		Gson gson = new Gson();
		Type userListType = new TypeToken<List<ApiUsers>>() {}.getType();
		usersList = gson.fromJson(response, userListType);
		return usersList;
		
	}
}
