package com.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.models.ApiUsers;

public class OktaApiHandler implements ApiResponseHandler{
	public List<ApiUsers> getUsersList(String url, String apiToken) throws IOException {
		List<ApiUsers> usersList = new ArrayList<>();
		URL apiUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "SSWS " + apiToken);
		conn.setRequestProperty("Accept", "application/json");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		
		JSONArray users = new JSONArray(sb.toString());
		for(int i = 0; i < users.length(); i++) {
			ApiUsers apiUser = new ApiUsers();
			JSONObject user = users.getJSONObject(i);
			JSONObject profile = user.getJSONObject("profile");
			apiUser.setUserName(profile.optString("firstName"));
			apiUser.setFirstName(profile.optString("firstName"));
			apiUser.setLastName(profile.optString("lastName"));
			apiUser.setMobileNum(profile.optString("mobilePhone"));
			apiUser.setEmail(profile.optString("email"));
			apiUser.setPassword(profile.optString("firstName"));
			usersList.add(apiUser);
		}
		return usersList;
	}
}
