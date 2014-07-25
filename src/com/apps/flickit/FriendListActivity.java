package com.apps.flickit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.flickit.networking.FlickrClient;
import com.apps.flickit.networking.MyCustomSender;
import com.loopj.android.http.JsonHttpResponseHandler;

public class FriendListActivity extends Activity implements OnItemClickListener {

	private ArrayList<String> stubEmails = new ArrayList<String>(Arrays.asList(
			"akashagarwal1988@yahoo.com", "vasanthykolluri@yahoo.com", "test",
			"test"));
	private ArrayAdapter<String> aStubEmails;
	ListView lvList;
	ArrayList<String> checkedEmails;
	FlickrClient client;
	List<String> nsids;
	String myName;
	String groupId;
	String groupName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_list);
		aStubEmails = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, stubEmails);
		ListAdapter listAdapter = new ListAdapter(this, stubEmails);
		lvList = (ListView) findViewById(R.id.lvFriendList);
		lvList.setAdapter(listAdapter);
		lvList.setOnItemClickListener(this);
		checkedEmails = new ArrayList<String>();
		nsids = new ArrayList<String>();
		client = FlickrClientApp.getRestClient();
		Intent intent = getIntent();
		String userName = intent.getStringExtra("user");
		groupId = intent.getStringExtra("groupId");
		groupName = intent.getStringExtra("groupName");
		getMyName();
		// Toast.makeText(getApplicationContext(), userName + " " + groupId +
		// " "+ groupName + " " + startDate + " " + endDate,
		// Toast.LENGTH_SHORT).show();

	}

	public void onSaveList(MenuItem mi) {
		Toast.makeText(this, "" + checkedEmails, Toast.LENGTH_LONG).show();
		for (String email : checkedEmails) {
			client.getUserByEmail(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject json) {
					Log.d("DEBUG", "result: " + json.toString());
					// Toast.makeText(getApplicationContext(), json.toString(),
					// Toast.LENGTH_SHORT).show();
					try {
						String nsid = json.getJSONObject("user").getString(
								"nsid");
						// Second call to get the real_name
						client.getInfo(new JsonHttpResponseHandler() {
							@Override
							public void onSuccess(JSONObject json) {
								Log.d("DEBUG", "result: " + json.toString());
								try {
									String nsid = json.getJSONObject("person")
											.getString("nsid");
									String userName = json
											.getJSONObject("person")
											.getJSONObject("realname")
											.getString("_content");
									// nsids.add(nsid);
									MyCustomSender.sendGroupAddReq(
											FlickrClientApp.getAppOwner()
													.getUserId(), myName, nsid,
											userName, groupId, groupName);

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							@Override
							public void onFailure(Throwable e, String s) {
								Log.d("debug", e.toString());
								Log.d("debug", s.toString());
								// Toast.makeText(getActivity(),
								// "Post Failed !", Toast.LENGTH_SHORT).show();
							}

						}, nsid);
						nsids.add(nsid);
						// Toast.makeText(getApplicationContext(),"userName " +
						// userName, Toast.LENGTH_SHORT).show();

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				@Override
				public void onFailure(Throwable e, String s) {
					Log.d("debug", e.toString());
					Log.d("debug", s.toString());
					// Toast.makeText(getActivity(), "Post Failed !",
					// Toast.LENGTH_SHORT).show();
				}

			}, email);

		}
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.savelist, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView arg0, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		CheckBox cb = (CheckBox) v.findViewById(R.id.checkBox1);
		TextView tv = (TextView) v.findViewById(R.id.textView1);
		cb.performClick();
		// Toast.makeText(this, tv.getText(), Toast.LENGTH_SHORT).show();
		if (cb.isChecked()) {
			checkedEmails.add(tv.getText().toString());
			// Toast.makeText(this, "Inside Checked",
			// Toast.LENGTH_SHORT).show();

		} else if (!cb.isChecked()) {
			checkedEmails.remove(tv.getText().toString());
			// Toast.makeText(this, "Inside else", Toast.LENGTH_SHORT).show();

		}

	}

	public String getMyName() {

		client.getInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				Log.d("DEBUG", "result: " + json.toString());
				try {
					// String nsid =
					// json.getJSONObject("person").getString("nsid");
					myName = json.getJSONObject("person")
							.getJSONObject("realname").getString("_content");
					// nsids.add(nsid);
					// MyCustomSender.sendGroupAddReq(FlickrClientApp.getAppOwner().getUserId(),
					// "my_userName", "friend1NSID", "friend1UserName",
					// "GroupId", "GroupName");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
				// Toast.makeText(getActivity(), "Post Failed !",
				// Toast.LENGTH_SHORT).show();
			}

		}, FlickrClientApp.getAppOwner().getUserId());
		return myName;
	}

}
