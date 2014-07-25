package com.apps.flickit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.apps.flickit.adapters.PhotoArrayAdapter;
import com.apps.flickit.models.FlickrPhoto;
import com.apps.flickit.models.Group;
import com.apps.flickit.networking.FlickrClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class PhotosActivity extends Activity {
	
	FlickrClient client;
	ArrayList<FlickrPhoto> photoItems;
	GridView gvPhotos;
	PhotoArrayAdapter adapter;
	String NSIDfromPhotoId;
	DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	
public Date dateParse(String date) throws ParseException{
	return sdf.parse(date);
}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photos);
		client = FlickrClientApp.getRestClient();
		photoItems = new ArrayList<FlickrPhoto>();
		gvPhotos = (GridView) findViewById(R.id.gvPhotos);
		adapter = new PhotoArrayAdapter(this, photoItems);
		gvPhotos.setAdapter(adapter);
		//loadPhotos();
		//findPeople();
		//postPicture();
		postPicture();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_group, menu);
		return true;
	}
	
	public void loadPhotos() {
		client.getInterestingnessList(new JsonHttpResponseHandler() { 
    		public void onSuccess(JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                // Add new photos to SQLite
                try {
					JSONArray photos = json.getJSONObject("photos").getJSONArray("photo");
					for (int x = 0; x < photos.length(); x++) {
						String uid  = photos.getJSONObject(x).getString("id");
						FlickrPhoto p = FlickrPhoto.byPhotoUid(uid);
						if (p == null) { p = new FlickrPhoto(photos.getJSONObject(x)); };
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
	
	public void findPeople() {
		client.getPeopleByName(new JsonHttpResponseHandler() { 
    		public void onSuccess(JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                Toast.makeText(getApplicationContext(), json.toString(), Toast.LENGTH_SHORT).show();
                
            }
    	});
	}
	
	
	public void postPicture() {
		
		Toast.makeText(getApplicationContext(), "Posting pic", Toast.LENGTH_SHORT).show();
		client.createPhotoPost( BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher),
				new JsonHttpResponseHandler() { 

			@Override
			public void onSuccess(JSONObject json) {
				Log.d("DEBUG", "result POST: " + json);
                Toast.makeText(getApplicationContext(), "Post SUCCESS", Toast.LENGTH_SHORT).show();
                String photoId = "";
                try {
				 photoId = json.getString("photoid");
                } catch (JSONException e) {
					e.printStackTrace();
				}
			
					client.getNsidFromPhotoId(photoId, new JsonHttpResponseHandler() { 

						@Override
						public void onSuccess(JSONObject json) {
							Log.d("DEBUG", "result POST: " + json);
							try {
								NSIDfromPhotoId = json.getJSONObject("photo").getJSONObject("owner").getString("nsid");
								String getId = json.getJSONObject("photo").getJSONObject("photo ").getString("id");
								Date startDateGrp1 = dateParse("07/23/2014");
								Date startDateGrp2 = dateParse("07/31/2014");
								Date endDateGrp1 = dateParse("07/28/2014");
								Date endDateGrp2 = dateParse("08/05/2014");
								ArrayList<Group> groups = new ArrayList<Group>(Arrays.asList(new Group("2667613@N20", "xyzasdf", "testURL", startDateGrp1, endDateGrp1), new Group("2727659@N22", "AlaskaTrip","test", startDateGrp2, endDateGrp2)));
								Date currentDate = new Date();
								for(Group group : groups) {
									if(group.getStartDate().before(currentDate) && group.getEndDate().after(currentDate)){
										client.addPhotosInGroups(group.getGroupId(), getId, new JsonHttpResponseHandler() { 

											@Override
											public void onSuccess(JSONObject json) {
												Log.d("DEBUG", "result POST: " + json);
												Toast.makeText(getApplicationContext(), ""+ json, Toast.LENGTH_SHORT);
											}
											
										});
									}
								}
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				
			});
			}
                
    		@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
				Toast.makeText(getApplicationContext(), "Post Failed !", Toast.LENGTH_SHORT).show();
			}
			
    	});
	}
	
	public void onAddGroup(MenuItem mi) {
		Intent intent = new Intent(this, AddGroupActivity.class);
		startActivity(intent);
	}
	
	
	public void onShowGroupsClick(MenuItem mi) {
		Intent intent = new Intent(this, GroupActivity.class);
		startActivity(intent);
	}
	
	public void onCameraClick(MenuItem mi) {
		postPicture();
	}

}
