package com.apps.flickit;

import java.util.Date;

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
		FlickrClientApp.getParseClient().addGroup("groupIdDummy", "nameDummy", "imgUrlDummy", dummy, dummy);
		FlickrClientApp.getParseClient().addUserGroup("userIdDummy", "groupIdDummy");
	}
}
