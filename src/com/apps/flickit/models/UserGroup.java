package com.apps.flickit.models;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("UserGroup")
public class UserGroup extends ParseObject {
	// subclass should have a public default constructor
	public UserGroup() {
		super();
	}

	public UserGroup(String userId, String groupId) {
		put("userId", userId);
		put("groupId", groupId);
	}

	public String getObjectId() {
		return getString("objectId");
	}

	public String getUserId() {
		return getString("userId");
	}

	public String getGroupId() {
		return getString("groupId");
	}

	public Date getCreatedAt() {
		// TODO Auto-generated method stub
		return getDate("createdAt");
	}

	public Date getUpdatedAt() {
		// TODO Auto-generated method stub
		return getDate("updatedAt");
	}
}
