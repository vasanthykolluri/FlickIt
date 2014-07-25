package com.apps.flickit.networking;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.apps.flickit.networking.*;
import com.apps.flickit.models.GroupAddReq;
import com.apps.flickit.models.GroupAddReqResp;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;

public class MyCustomSender {
	private static final String TAG = "MyCustomSender";

	public static void sendGroupAddReq(String senderId, String senderName,
			String receiverId, String receiverName, String groupId,
			String groupName) {

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("alert", "Hello " + receiverName + "," + senderName
					+ " here...");
			obj.put("action", MyCustomReceiver.intentActionGroupAddReq);
			GroupAddReq groupAddReq = new GroupAddReq(senderId, senderName,
					receiverId, receiverName, groupId, groupName);
			obj.put("groupAddReq", GroupAddReq.toJson(groupAddReq));
			
			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();

			// Push the notification to Android users
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			// Push the notification to a specific user's channel
			push.setChannel(MyUtils.getChannelName(receiverId));
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(C(),
					// "Sent GROUP_ADD_REQ", Toast.LENGTH_LONG).show();
				}

			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void sendGroupAddReqResp(GroupAddReq groupAddReq, boolean response) {

		Log.d(TAG, groupAddReq.getSenderId() + " " + groupAddReq.getSenderName());
		Log.d(TAG, groupAddReq.getReceiverId() + " " + groupAddReq.getReceiverName());

		
		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("action", MyCustomReceiver.intentActionGroupAddReqResp);
			GroupAddReqResp groupAddReqResp = new GroupAddReqResp(
					groupAddReq.getReceiverId(), groupAddReq.getReceiverName(),
					groupAddReq.getSenderId(), groupAddReq.getSenderName(),
					groupAddReq.getGroupId(), groupAddReq.getGroupName(),
					response);
			obj.put("alert", "FlickIt! Response from " + groupAddReqResp.getSenderName());
			obj.put("groupAddReqResp", GroupAddReqResp.toJson(groupAddReqResp));

			Log.d(TAG, "Resp Sender" + groupAddReqResp.getSenderId() + " " + groupAddReqResp.getSenderName());
			Log.d(TAG, "Resp rcvr" + groupAddReqResp.getReceiverId() + " " + groupAddReqResp.getReceiverName());
			
			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			// Send response on sender's channel
			push.setChannel(MyUtils.getChannelName(groupAddReqResp.getReceiverId()));
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(getApplicationContext(),
					// "Done with sending", Toast.LENGTH_LONG).show();
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
