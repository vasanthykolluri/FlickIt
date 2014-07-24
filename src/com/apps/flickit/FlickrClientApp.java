package com.apps.flickit;

import android.content.Context;

import com.apps.flickit.models.Group;
import com.apps.flickit.models.UserGroups;
import com.apps.flickit.networking.FlickrClient;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class FlickrClientApp extends com.activeandroid.app.Application {
	private static Context context;

	public static final String userName = "Vasanthy";

	@Override
	public void onCreate() {
		super.onCreate();
		FlickrClientApp.context = this;


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
		ParseObject.registerSubclass(UserGroups.class);
		// ParseObject.registerSubclass(Settings.class);

		// Add your initialization code here
		Parse.initialize(this, "5zdyWHr3dCZmXDFmJbi3OyAUWTj3Yhqnp2IQPVK8",
				"AygCtUYA7TVRwgE42yrckLELWqdDppfATI12XMsy");
		ParseInstallation parseInstallation = ParseInstallation
				.getCurrentInstallation();

		PushService.setDefaultPushCallback(this, PhotosActivity.class);

		// PushService.subscribe(context, TrackABuddyApp.userName,
		// HandleTrackReqActivity.class);
		// PushService.subscribe(context, TrackABuddyApp.userName,
		// ShowPopUpResponse.class)

		// ParseAnalytics.trackAppOpened(getIntent());
		parseInstallation.getInstallationId();
		parseInstallation.put("username", userName);
		parseInstallation.saveInBackground();
	}
}