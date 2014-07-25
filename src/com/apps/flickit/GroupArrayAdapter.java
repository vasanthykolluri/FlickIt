package com.apps.flickit;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.flickit.models.Group;

public class GroupArrayAdapter extends ArrayAdapter<Group> {

	public GroupArrayAdapter(Context context, List<Group> groups) {
		super(context, 0, groups);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Group group = getItem(position);

		// Find or inflate the template
		View v;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.group_item, parent, false);
		} else {
			v = convertView;
		}

		// Find the views within template
		TextView tvGroupName = (TextView) v.findViewById(R.id.tvGroupName);
		TextView tvStartDate = (TextView) v.findViewById(R.id.tvStartDate);
		TextView tvEndDate = (TextView) v.findViewById(R.id.tvEndDate);

		// Populate the data into the template view using the data object
		tvGroupName.setText(group.getName());
		tvStartDate.setText(group.getStartDate().toString());
		tvEndDate.setText(group.getEndDate().toString());

			// Return the completed view to render on screen
		return v;
	}
}