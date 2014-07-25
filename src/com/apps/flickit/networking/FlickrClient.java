package com.apps.flickit.networking;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;

import android.content.Context;
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
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
        setBaseUrl("https://api.flickr.com/services/rest");
    }

    public void getInterestingnessList(AsyncHttpResponseHandler handler) {
    
        String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.interestingness.getList");
        Log.d("DEBUG", "Sending API call to " + apiUrl);
        RequestParams params = new RequestParams();
		params.put("format", "json");
        client.get(apiUrl, params, handler);
    }

    public void getMyUserProfile(JsonHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?&format=json&nojsoncallback=1&method=flickr.urls.getUserProfile");
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
	public void postPic(AsyncHttpResponseHandler handler, String photo){
		String postUrl = "https://up.flickr.com/services/upload/?photo=" + photo;
		Log.d("DEBUG", "Sending API call to " + postUrl);
		RequestParams params = new RequestParams();
			params.put("format","json");		
		client.post(postUrl, params, handler);
	}
}