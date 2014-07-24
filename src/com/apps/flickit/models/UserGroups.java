package com.apps.flickit.models;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("UserGroups")
public class UserGroups extends ParseObject {
	// subclass should have a public default constructor
	public UserGroups() {
		super();
	}

	public UserGroups(String userId, String groupId, String imgUrl,
			Date startDate, Date endDate) {
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
