package com.apps.flickit.networking;

import java.util.Date;

import android.content.Context;

import com.apps.flickit.models.Group;
import com.apps.flickit.models.UserGroup;

public class ParseClient {
	// public void getBuddy(String buddyId) {
	// // Specify which class to query
	// ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
	// // Specify the object id
	// query.getInBackground(buddyId, new GetCallback<Buddy>() {
	// public void done(Buddy item, ParseException e) {
	// if (e == null) {
	// // Access data using the `get` methods for the object
	// String name = item.getBuddyScreenName();
	// String imgUrl = item.getImgUrl();
	// String city = item.getCity();
	// Long distance = item.getDistance();
	//
	// // Access special values that are built-in to each object
	// String objectId = item.getObjectId();
	// Date createdAt = item.getCreatedAt();
	// Date updatedAt = item.getUpdatedAt();
	//
	// // Do whatever you want with the data...
	// // Toast.makeText(MainActivity.this, name + " " + city + " "
	// // + distance,
	// // Toast.LENGTH_SHORT).show();
	// } else {
	// Log.d("item", "Error: " + e.getMessage());
	// }
	// }
	// });
	// }

	public void addGroup(String groupId, String name, String imgUrl,
			Date startDate, Date endDate) {
        //ToDo:ensure groupId is unique
		Group group = new Group(groupId, name, imgUrl, startDate, endDate);
		group.saveInBackground();
	}

	public void addUserGroup(String userId, String groupId) {
		//ToDo:ensure userId,groupId is unique
		UserGroup userGroup = new UserGroup(userId, groupId);
		userGroup.saveInBackground();
	}
	
	public static ParseClient getInstance(Class<ParseClient> class1,
			Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}
