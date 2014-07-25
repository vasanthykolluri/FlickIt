package com.apps.flickit;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.apps.flickit.models.Photo;
import com.apps.flickit.networking.FlickrClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class GroupPicsActivity extends Activity {

	FlickrClient client;
	GridView gvGroupPics;
	ArrayList<Photo> photos = new ArrayList<Photo>();
	GroupPicsArrayAdapter groupPicsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = FlickrClientApp.getRestClient();
		String groupId = getIntent().getStringExtra("groupId");

		setContentView(R.layout.activity_group_pics);
		gvGroupPics = (GridView) findViewById(R.id.gvGroupPics);

		groupPicsAdapter = new GroupPicsArrayAdapter(this, photos);
		gvGroupPics.setAdapter(groupPicsAdapter);

		getGroupPics(groupId);
	}

	public void getGroupPics(String groupId) {
		client.getGroupPics(groupId, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject json) {

				Log.d("DEBUG", "result: " + json.toString());
				try {
					JSONArray photoJsons = json.getJSONObject("photos")
							.getJSONArray("photo");
					groupPicsAdapter.clear();
					groupPicsAdapter.addAll(Photo.fromJSONArray(photoJsons));
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("debug", e.toString());
				}
			}
		});
	}
}
