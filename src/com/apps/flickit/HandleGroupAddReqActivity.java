package com.apps.flickit;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.apps.flickit.models.GroupAddReq;
import com.apps.flickit.models.GroupAddReqResp;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;

public class HandleGroupAddReqActivity extends Activity implements OnClickListener {

	Button accept;
	Button decline;

	boolean click = true;

	private GroupAddReq groupAddReq;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		groupAddReq = (GroupAddReq) getIntent().getSerializableExtra(
				"groupAddReq");
		setTitle(getIntent().getStringExtra("message"));
		setContentView(R.layout.popup_groupaddreq);
		accept = (Button) findViewById(R.id.btnAccept);
		accept.setOnClickListener(this);
		decline = (Button) findViewById(R.id.btnDecline);
		decline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnAccept) {
			sendTrackingRequestResponse(true);
		} else if (v.getId() == R.id.btnDecline) {
			sendTrackingRequestResponse(false);
		}
		finish();
	}

	private void sendTrackingRequestResponse(boolean response) {

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("alert", "Group Add Response!");
			obj.put("action", MyCustomReceiver.intentActionGroupAddReqResp);
			GroupAddReqResp groupAddReqResp = new GroupAddReqResp(
					groupAddReq.getUserId(), groupAddReq.getUserName(), groupAddReq.getGroupId(),
					groupAddReq.getGroupName(), response);
			obj.put("groupAddReqResp", GroupAddReqResp.toJson(groupAddReqResp));

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();

			// Send response on sender's channel
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			push.setChannel(groupAddReq.getUserId());
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(getApplicationContext(),
					// "Done with sending", Toast.LENGTH_LONG).show();
				}
			});

			// Add sender as buddy to db
			//addSendertoDB();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

//	private void addSendertoDB() {
//		TrackABuddyApp.parseClient.addBuddy(senderLocation.getName(),
//				senderLocation.getImgUrl(), senderLocation.getCity());
//
//	}
}