package com.apps.flickit.networking;

public class MyUtils {
	public static String getChannelName(String userId) {
		return ("ch" + userId.replace("@", "at"));
	}

}
