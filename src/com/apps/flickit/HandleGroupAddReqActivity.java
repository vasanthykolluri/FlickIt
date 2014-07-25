package com.apps.flickit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.apps.flickit.models.GroupAddReq;
import com.apps.flickit.networking.MyCustomSender;

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
		String message = getIntent().getStringExtra("message");
		
		setTitle(message);
		setContentView(R.layout.popup_groupaddreq);
		accept = (Button) findViewById(R.id.btnAccept);
		accept.setOnClickListener(this);
		decline = (Button) findViewById(R.id.btnDecline);
		decline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnAccept) {
			MyCustomSender.sendGroupAddReqResp(groupAddReq, true);
		} else if (v.getId() == R.id.btnDecline) {
			MyCustomSender.sendGroupAddReqResp(groupAddReq, false);
		}
		finish();
	}

	

//	private void addSendertoDB() {
//		TrackABuddyApp.parseClient.addBuddy(senderLocation.getName(),
//				senderLocation.getImgUrl(), senderLocation.getCity());
//
//	}
}