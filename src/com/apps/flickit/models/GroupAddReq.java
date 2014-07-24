package com.apps.flickit.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupAddReq implements Serializable {
	
	private static final long serialVersionUID = 9157292014655616918L;
	
	private String userId;
	private String userName;
	private String groupId;
	private String groupName;

	public GroupAddReq() {
		// TODO Auto-generated constructor stub
	}
	
	public GroupAddReq(String userId, String userName, String groupId,
			String groupName) {
		this.userId = userId;
		this.userName = userName;
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public String getUserId() {
		return userId;
	}


	public String getUserName() {
		return userName;
	}


	public String getGroupId() {
		return groupId;
	}


	public String getGroupName() {
		return groupName;
	}

	public static GroupAddReq fromJson(JSONObject jsonObject) {
		GroupAddReq groupAddReq = new GroupAddReq();

		try {
			groupAddReq.userId = jsonObject.getString("userId");
			groupAddReq.userName = jsonObject.getString("userName");
			groupAddReq.groupId = jsonObject.getString("groupId");
			groupAddReq.groupName = jsonObject.getString("groupName");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return groupAddReq;
	}

	public static JSONObject toJson(GroupAddReq groupAddReq) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("userId", groupAddReq.userId);
			jsonObject.put("userName", groupAddReq.userName);
			jsonObject.put("groupId", groupAddReq.groupId);
			jsonObject.put("groupName", groupAddReq.groupName);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
