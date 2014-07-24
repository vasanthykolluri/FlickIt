package com.apps.flickit.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupAddReqResp implements Serializable {
	
	private static final long serialVersionUID = -4832201350462873804L;
	
	private String userId;
	private String userName;
	private String groupId;
	private String groupName;
	private Boolean response;

	public GroupAddReqResp() {
		// TODO Auto-generated constructor stub
	}
	
	public GroupAddReqResp(String userId, String userName, String groupId,
			String groupName, Boolean response) {
		this.userId = userId;
		this.userName = userName;
		this.groupId = groupId;
		this.groupName = groupName;
		this.response = response;
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

	public Boolean getResponse() {
		return response;
	}

	public static GroupAddReqResp fromJson(JSONObject jsonObject) {
		GroupAddReqResp groupAddReqResp = new GroupAddReqResp();

		try {
			groupAddReqResp.userId = jsonObject.getString("userId");
			groupAddReqResp.userName = jsonObject.getString("userName");
			groupAddReqResp.groupId = jsonObject.getString("groupId");
			groupAddReqResp.groupName = jsonObject.getString("groupName");
			groupAddReqResp.response = jsonObject.getBoolean("response");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return groupAddReqResp;
	}

	public static JSONObject toJson(GroupAddReqResp groupAddReqResp) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("userId", groupAddReqResp.userId);
			jsonObject.put("userName", groupAddReqResp.userName);
			jsonObject.put("groupId", groupAddReqResp.groupId);
			jsonObject.put("groupName", groupAddReqResp.groupName);
			jsonObject.put("response", groupAddReqResp.response);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
