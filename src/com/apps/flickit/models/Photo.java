package com.apps.flickit.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Photo implements Serializable {

	private static final long serialVersionUID = -7744223549930329195L;

	private String id;
	private String ownerId;
	private String secret;
	private String server;
	private String farm;
	private String title;

	public String getId() {
		return id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public String getSecret() {
		return secret;
	}

	public String getServer() {
		return server;
	}

	public String getFarm() {
		return farm;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		// thumbnail urls
		String url = "https://farm" + farm + ".staticflickr.com/" + server + 
		  "/" + id + "_" + secret + "_t.jpg";
		return url;
	}
	
	public static Photo fromJson(JSONObject jsonObject) {
		Photo photo = new Photo();

		try {
			photo.id = jsonObject.getString("id");
			photo.ownerId = jsonObject.getString("owner");
			photo.secret = jsonObject.getString("secret");
			photo.server = jsonObject.getString("server");
			photo.farm = jsonObject.getString("farm");
			photo.title = jsonObject.getString("title");

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return photo;
	}

	public static ArrayList<Photo> fromJSONArray(JSONArray jsonArray) {
		ArrayList<Photo> photos = new ArrayList<Photo>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject photoJson = null;
			try {
				photoJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Photo photo = Photo.fromJson(photoJson);

			if (photo != null) {
				photos.add(photo);
			}
		}

		return photos;
	}
}
