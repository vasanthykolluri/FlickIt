package com.apps.flickit.networking;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Deflater;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FlickrClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = FlickrApi.class;
	public static final String REST_URL = "https://www.flickr.com/services/oauth";
	public static final String REST_CONSUMER_KEY = "62854b1d786d8621e4941a11e0875990";
	public static final String REST_CONSUMER_SECRET = "426eb74291c61186";
	public static final String REST_CALLBACK_URL = "oauth://cprest";

	public FlickrClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY,
				REST_CONSUMER_SECRET, REST_CALLBACK_URL);
		setBaseUrl("https://api.flickr.com/services/rest");
	}

	public void getInterestingnessList(AsyncHttpResponseHandler handler) {

		String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.interestingness.getList");
		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}

	public void getGroupPics(String groupId, AsyncHttpResponseHandler handler) {

		String myGroupId=groupId.replace("@", "%40");
		String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.groups.pools.getPhotos&group_Id="+groupId);
		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		params.put("format", "json");
		groupId.replace("@", "%40");
		params.put("group_id", groupId);
		client.get(apiUrl, params, handler);
	}

	public void getMyUserProfile(JsonHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.urls.getUserProfile");
		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}

	public void getUserByEmail(JsonHttpResponseHandler handler, String email) {
		String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.people.findByEmail&find_email="
				+ email);
		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}

	public void getPeopleByName(JsonHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.people.findByEmail&find_email=akashagarwal1988@yahoo.com");
		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);

	}

	public void getInfo(JsonHttpResponseHandler handler, String userId) {
		String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.people.getInfo&user_id="
				+ userId);
		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);

	}

	public void createPhotoPost(Bitmap bitmap,
			final AsyncHttpResponseHandler handler) {
		String apiUrl = "https://up.flickr.com/services/upload/?tags=cpflickrsnap&is_public=1&content_type=1&hidden=1";
		RequestParams params = new RequestParams();
		params.put("tags", "cpflickrsnap");
		params.put("is_public", "1"); // 1 for yes
		params.put("content_type", "1"); // Set to 1 for Photo
		params.put("hidden", "1"); // Set to 1 to keep the photo in global
									// search results

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		final byte[] bytes = stream.toByteArray();
		params.put("photo", new ByteArrayInputStream(bytes), "image.png");
		Log.d("DEBUG", "Sending API call to " + apiUrl);

		client.post(apiUrl, params, handler);
	}

	public void getNsidFromPhotoId(String photoId,
			JsonHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.photos.getInfo&photo_id=" + photoId);
		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
		
	}

	public void addPhotosInGroups(String groupId, String photoId,
			AsyncHttpResponseHandler handler) {
		//String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.groups.pools.add&photo_id=" + photoId + "&group_id=" + groupId);
		String apiUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.pools.add";

		Log.d("DEBUG", "Sending API call to " + apiUrl);
		RequestParams params = new RequestParams();
		//params.put("format", "json");
		params.put("photo_id", photoId);
		params.put("group_id", groupId);
		client.post(apiUrl, params, handler);
		
	}

}