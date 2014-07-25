package com.apps.flickit.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class MyTestService extends IntentService {

	public MyTestService() {
		super("test-service");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	  protected void onHandleIntent(Intent intent) {
	    // This describes what will happen when service is triggered
		// Extract the receiver passed into the service
	    ResultReceiver rec = intent.getParcelableExtra("receiver");
	    // Extract additional values from the bundle
	    String val = intent.getStringExtra("foo");
	    // To send a message to the Activity, create a pass a Bundle
	    Bundle bundle = new Bundle();
	    bundle.putString("resultValue", "My Result Value. Passed in: " + val);
	    // Here we call send passing a resultCode and the bundle of extras
	    rec.send(Activity.RESULT_OK, bundle);
	  }

	

}
