package com.apps.flickit.networking;

public class MyUtils {
	public static String getChannelName(String userId) {
		return ("ch" + userId.replace("@", "at"));
	}

	public static String getPhotoIdFromXml(String xml) {		
		int startPosition = xml.indexOf("<photoid>") + "<photoid>".length();  
		int endPosition = xml.indexOf("</photoid>", startPosition);  
		String photoId = xml.substring(startPosition, endPosition);  
		return photoId;
	}
}
