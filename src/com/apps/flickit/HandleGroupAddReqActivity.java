package com.apps.flickit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.apps.flickit.models.GroupAddReq;
import com.apps.flickit.networking.MyCustomSender;

public class HandleGroupAddReqActivity extends Activity implements OnClickListener {

	Button accept;
	Button decline;

	boolean click = true;

	private GroupAddReq groupAddReq;
	private TextView tvGroupAddReq;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_groupaddreq);
		tvGroupAddReq = (TextView) findViewById(R.id.tvGroupAddReq);

		groupAddReq = (GroupAddReq) getIntent().getSerializableExtra(
				"groupAddReq");
		String message = groupAddReq.getSenderName() + " wants to add you to group " + groupAddReq.getGroupName();
		
		setTitle("FlickIt!");
		tvGroupAddReq.setText(message);
		
		accept = (Button) findViewById(R.id.btnAccept);
		accept.setOnClickListener(this);
		decline = (Button) findViewById(R.id.btnDecline);
		decline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnAccept) {
			MyCustomSender.sendGroupAddReqResp(groupAddReq, true);
			// Add user to group in Parse db
			FlickrClientApp.getParseClient().addUserGroup(groupAddReq.getReceiverId(), groupAddReq.getGroupId());
		} else if (v.getId() == R.id.btnDecline) {
			MyCustomSender.sendGroupAddReqResp(groupAddReq, false);
		}
		finish();
	}
}