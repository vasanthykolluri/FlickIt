package com.apps.flickit.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupAddReqResp implements Serializable {

	private static final long serialVersionUID = -4832201350462873804L;

	private String senderId;
	private String senderName;
	private String receiverId;
	private String receiverName;
	private String groupId;
	private String groupName;
	private Boolean response;

	public GroupAddReqResp() {
		// TODO Auto-generated constructor stub
	}

	public GroupAddReqResp(String senderId, String senderName,
			String receiverId, String receiverName, String groupId,
			String groupName, Boolean response) {
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.groupId = groupId;
		this.groupName = groupName;
		this.response = response;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public String getReceiverName() {
		return receiverName;
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
			groupAddReqResp.senderId = jsonObject.getString("senderId");
			groupAddReqResp.senderName = jsonObject.getString("senderName");
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
			jsonObject.put("senderId", groupAddReqResp.senderId);
			jsonObject.put("senderName", groupAddReqResp.senderName);
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
