package com.apps.flickit;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.apps.flickit.models.Photo;
import com.loopj.android.image.SmartImageView;

public class GroupPicsArrayAdapter extends ArrayAdapter<Photo> {

	public GroupPicsArrayAdapter(Context context, List<Photo> photos) {
		super(context, R.layout.grouppic_item, photos);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Photo photo = this.getItem(position);
		SmartImageView ivImage;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflator.inflate(R.layout.grouppic_item, parent, false);
		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(photo.getUrl());
		Log.d("DEBUG", "ivImageUrl=" + photo.getUrl());
		return ivImage;
	}

}