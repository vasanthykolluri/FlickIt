package com.apps.flickit.models;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Group")
public class Group extends ParseObject {
	// subclass should have a public default constructor
	public Group() {
		super();
	}

	public Group(String groupId, String name, String imgUrl, Date startDate,
			Date endDate) {
		put("groupId", groupId);
		put("name", name);
		put("imgUrl", imgUrl);
		put("startDate", startDate);
		put("endDate", endDate);
	}

	public String getObjectId() {
		return getString("objectId");
	}

	public String getGroupId() {
		return getString("groupId");
	}

	public String getName() {
		return getString("name");
	}

	public String getImgUrl() {
		return getString("imgUrl");
	}

	public Date getStartDate() {
		// TODO Auto-generated method stub
		return getDate("startDate");
	}

	public Date getEndDate() {
		// TODO Auto-generated method stub
		return getDate("endDate");
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
