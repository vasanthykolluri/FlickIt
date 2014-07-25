package com.apps.flickit.networking;

import org.json.JSONException;
import org.json.JSONObject;

import com.apps.flickit.FlickrClientApp;
import com.apps.flickit.HandleGroupAddReqActivity;
import com.apps.flickit.models.GroupAddReq;
import com.apps.flickit.models.GroupAddReqResp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	public static final String intentActionGroupAddReq = "GROUP_ADD_REQ";
	public static final String intentActionGroupAddReqResp = "GROUP_ADD_REQ_RESP";

	@Override
	public void onReceive(Context context, Intent intent) {
		//Toast.makeText(context.getApplicationContext(), "MyCustomReceiver - onReceive", Toast.LENGTH_LONG).show();
		try {
			if (intent == null) {
				Log.d(TAG, "Receiver intent null");
			} else {
				String action = intent.getAction();
				Log.d(TAG, "got action " + action);
				if (action.equals(intentActionGroupAddReq)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					// Filter on user's channel
					if (channel.equals(MyUtils.getChannelName(FlickrClientApp.getAppOwner().getUserId()))) {
						JSONObject json = new JSONObject(intent.getExtras()
								.getString("com.parse.Data"));

						Log.d(TAG, "got action " + action + " on channel "
								+ channel);
						GroupAddReq groupAddReq = GroupAddReq
								.fromJson(json.getJSONObject("groupAddReq"));

						// Handle push notification by invoking activity
						// directly
						Intent pupInt = new Intent(context,
								HandleGroupAddReqActivity.class);
						pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupInt.putExtra("groupAddReq", groupAddReq);
						context.getApplicationContext().startActivity(pupInt);

						// Handle push notification by sending a local
						// broadcast
						// to which the activity
						// subscribes to
						// LocalBroadcastManager.getInstance(context)
						// .sendBroadcast(
						// new Intent(intentActionTrackReq));

					}
				} else if (action.equals(intentActionGroupAddReqResp)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras()
							.getString("com.parse.Data"));

					Log.d(TAG, "got action " + action + " on channel "
							+ channel);
					// Filter on user's channel
					if (channel.equals(MyUtils.getChannelName(FlickrClientApp.getAppOwner().getUserId()))) {

						GroupAddReqResp groupAddReqResp = GroupAddReqResp
								.fromJson(json.getJSONObject("groupAddReqResp"));

						// Handle push notification by invoking activity
						// directly

//						if (acceptFlag == true) {
//							// Add buddy to db
//							FlickrClientApp.getRestClient().addBuddy(
//									buddyLocation.getName(),
//									buddyLocation.getImgUrl(),
//									buddyLocation.getCity());
//						}
//
//						Intent pupRespInt = new Intent(context,
//								ShowPopUpResponse.class);
//						pupRespInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//						pupRespInt.putExtra("buddyName",
//								groupAddReqResp.getName());
//						context.getApplicationContext().startActivity(
//								pupRespInt);
					}
				}
			}
		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}