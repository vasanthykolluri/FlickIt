package com.apps.flickit.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Serializable {
	private String userId;
	private String userName;
	private String realName;

	public User(String userId) {
		this.userId = userId;
	}
	
	public User(String userId, String userName, String realName) {
		this.userId = userId;
		this.userName = userName;
		this.realName = realName;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getRealName() {
		return realName;
	}

	public static User fromJson(JSONObject jsonObject) {
		User user = new User();

		try {
			user.userId = jsonObject.getString("userId");
			user.userName = jsonObject.getString("userName");
			user.realName = jsonObject.getString("realName");
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return user;
	}

	public static JSONObject toJson(User user) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("userId", user.userId);
			jsonObject.put("userName", user.userName);
			jsonObject.put("realName", user.realName);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
