package com.apps.flickit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowGroupAddReqResp extends Activity implements OnClickListener {

	TextView tvTrackReqResponse;
	Button btnOK;
	String friendName;
	String groupName;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Boolean response = getIntent().getBooleanExtra("response", false);
		friendName = getIntent().getStringExtra("friendName");
		groupName = getIntent().getStringExtra("groupName");

		setContentView(R.layout.popup_groupaddreqresp);
		tvTrackReqResponse = (TextView) findViewById(R.id.tvTrackReqResp);
		btnOK = (Button) findViewById(R.id.btnOK);
		if (response == true) {
			tvTrackReqResponse.setText("Yayy!!!" + friendName + " joined group " + groupName);
		} else {
			tvTrackReqResponse.setText("Oops!!!" + friendName + " did not join group " + groupName);
		}
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}