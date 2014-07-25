package com.apps.flickit;

import java.util.Date;

import com.apps.flickit.networking.MyCustomSender;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AddGroupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_group, menu);
		return true;
	}
	public void onSaveGroup(MenuItem mi){
		Toast.makeText(getApplicationContext(), "onSaveGroup", Toast.LENGTH_SHORT).show();
		// ToDo: add the appropriate values
		Date dummy = new Date();
		FlickrClientApp.getParseClient().addGroup("2657656@N25", "Test-vas", "imgUrlDummy", dummy, dummy);
		FlickrClientApp.getParseClient().addUserGroup(FlickrClientApp.getAppOwner().getUserId(), "2657656@N25");
		
		MyCustomSender.sendGroupAddReq(FlickrClientApp.getAppOwner().getUserId(), "vasanthy", "125629891@N03", "vasanthy", "2657656@N25", "Test-vas");

		//MyCustomSender.sendGroupAddReq(FlickrClientApp.getAppOwner().getUserId(), "vasanthy", "117036493@N07", "akash", "2657656@N25", "Test-vas");
	}
}
