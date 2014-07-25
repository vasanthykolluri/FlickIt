package com.apps.flickit;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FriendListActivity extends Activity {
	
	private ArrayList<String> stubEmails = new ArrayList<String>(Arrays.asList("akashagarwal1988@yahoo.com", "vasanthykolluri@yahoo.com"));
	private ArrayAdapter<String> aStubEmails;
	ListView lvList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_list);
		aStubEmails = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, stubEmails);
		lvList = (ListView) findViewById(R.id.lvFriendList);
		lvList.setAdapter(aStubEmails);	
		Intent intent = getIntent();
		String userName = intent.getStringExtra("user");
		String groupId = intent.getStringExtra("groupId");
		String groupName = intent.getStringExtra("groupName");
		String startDate = intent.getStringExtra("startDate");
		String endDate = intent.getStringExtra("endDate");
		Toast.makeText(getApplicationContext(), userName + " " + groupId + " "+ groupName + " " + startDate + " " + endDate, Toast.LENGTH_SHORT).show();
		
	}
	
}
