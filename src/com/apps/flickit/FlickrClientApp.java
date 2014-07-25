package com.apps.flickit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.apps.flickit.models.FlickrPhoto;
import com.apps.flickit.models.Group;
import com.apps.flickit.models.User;
import com.apps.flickit.models.UserGroup;
import com.apps.flickit.networking.FlickrClient;
import com.apps.flickit.networking.MyUtils;
import com.apps.flickit.networking.ParseClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class FlickrClientApp extends com.activeandroid.app.Application {
	private static Context context;
	private static User owner;
	private static ParseClient parseClient;

	public static final String userName = "Vasanthy";

	@Override
	public void onCreate() {
		super.onCreate();
		FlickrClientApp.context = this;

		// Get the owner info
		setAppOwner();
		parseClient = new ParseClient();

		// Create global configuration and initialize ImageLoader with this
		// configuration
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
				defaultOptions).build();
		ImageLoader.getInstance().init(config);

		// Register app with Parse
		registerParse();
	}

	public static FlickrClient getRestClient() {
		return (FlickrClient) FlickrClient.getInstance(FlickrClient.class,
				FlickrClientApp.context);
	}

	private void registerParse() {

		// Register your parse models
		ParseObject.registerSubclass(Group.class);
		ParseObject.registerSubclass(UserGroup.class);
		// ParseObject.registerSubclass(Settings.class);

		// Add your initialization code here
		Parse.initialize(this, "5zdyWHr3dCZmXDFmJbi3OyAUWTj3Yhqnp2IQPVK8",
				"AygCtUYA7TVRwgE42yrckLELWqdDppfATI12XMsy");
		ParseInstallation parseInstallation = ParseInstallation
				.getCurrentInstallation();

		PushService.setDefaultPushCallback(this, PhotosActivity.class);

		// PushService.subscribe(context, TrackABuddyApp.userName,
		// ShowPopUpResponse.class)

		// ParseAnalytics.trackAppOpened(getIntent());
		parseInstallation.getInstallationId();
		parseInstallation.saveInBackground();
	}

	public static ParseClient getParseClient() {
		return parseClient;
	}

	public static User getAppOwner() {
		return owner;
	}

	private void setAppOwner() {
		owner = new User("0");
		getRestClient().getMyUserProfile(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				try {
					String userId = json.getJSONObject("user")
							.getString("nsid");
					owner.setUserId(userId);
					ParseInstallation.getCurrentInstallation().put("username",
							userId);
					// Subscribe to receiving on specific channels
					PushService.subscribe(context,
							MyUtils.getChannelName(userId),
							HandleGroupAddReqActivity.class);
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("debug", e.toString());
				}
			}

			@Override
			public void onFailure(Throwable e) {
				e.printStackTrace();
			}
		});
	}
}