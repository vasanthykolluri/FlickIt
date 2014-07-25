package com.apps.flickit.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupAddReq implements Serializable {

	private static final long serialVersionUID = 9157292014655616918L;

	private String senderId;
	private String senderName;
	private String receiverId;
	private String receiverName;
	private String groupId;
	private String groupName;

	public GroupAddReq() {
		// TODO Auto-generated constructor stub
	}

	public GroupAddReq(String senderId, String senderName, String receiverId,
			String receiverName, String groupId, String groupName) {
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.groupId = groupId;
		this.groupName = groupName;
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

	public static GroupAddReq fromJson(JSONObject jsonObject) {
		GroupAddReq groupAddReq = new GroupAddReq();

		try {
			groupAddReq.senderId = jsonObject.getString("senderId");
			groupAddReq.senderName = jsonObject.getString("senderName");
			groupAddReq.receiverId = jsonObject.getString("receiverId");
			groupAddReq.receiverName = jsonObject.getString("receiverName");
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
			jsonObject.put("senderId", groupAddReq.senderId);
			jsonObject.put("senderName", groupAddReq.senderName);
			jsonObject.put("receiverId", groupAddReq.receiverId);
			jsonObject.put("receiverName", groupAddReq.receiverName);
			jsonObject.put("groupId", groupAddReq.groupId);
			jsonObject.put("groupName", groupAddReq.groupName);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
