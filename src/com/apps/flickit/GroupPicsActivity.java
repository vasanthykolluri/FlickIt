package com.apps.flickit;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apps.flickit.adapters.PhotoArrayAdapter;
import com.apps.flickit.models.FlickrPhoto;
import com.apps.flickit.networking.FlickrClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

public class GroupPicsActivity extends Activity {

	FlickrClient client;
	ArrayList<FlickrPhoto> photoItems;
	GridView gvPhotos;
	PhotoArrayAdapter adapter;
	private String groupId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_pics);

		client = FlickrClientApp.getRestClient();
		groupId = getIntent().getStringExtra("groupId");
		photoItems = new ArrayList<FlickrPhoto>();
		gvPhotos = (GridView) findViewById(R.id.gvPhotos);
		adapter = new PhotoArrayAdapter(this, photoItems);
		gvPhotos.setAdapter(adapter);
		Toast.makeText(this, "groupId = " + groupId, Toast.LENGTH_SHORT).show();

		getGroupPics();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.add_group, menu);
		return true;
	}

	public void getGroupPics() {
		Toast.makeText(this, "getGroupPics", Toast.LENGTH_SHORT).show();
		client.getGroupPics(groupId, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject json) {
				Log.d("DEBUG", "result: " + json.toString());
				// Add new photos to SQLite
				try {
					JSONArray photos = json.getJSONObject("photos")
							.getJSONArray("photo");
					for (int x = 0; x < photos.length(); x++) {
						String uid = photos.getJSONObject(x).getString("id");
						FlickrPhoto p = FlickrPhoto.byPhotoUid(uid);
						if (p == null) {
							p = new FlickrPhoto(photos.getJSONObject(x));
						}
						;
						p.save();
					}
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("debug", e.toString());
				}

				// Load into GridView from DB
				for (FlickrPhoto p : FlickrPhoto.recentItems()) {
					adapter.add(p);
				}
				Log.d("DEBUG", "Total: " + photoItems.size());
			}
		});
	}

}
