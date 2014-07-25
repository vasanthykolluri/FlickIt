package com.apps.flickit;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.apps.flickit.models.Group;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class GroupActivity extends Activity {

	private ArrayList<Group> groups;
	private GroupArrayAdapter aGroups;
	private ListView lvGroups;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groups);

		lvGroups = (ListView) findViewById(R.id.lvGroups);
		groups = new ArrayList<Group>();
		aGroups = new GroupArrayAdapter(this, groups);
		lvGroups.setAdapter(aGroups);

		lvGroups.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(view.getContext(),
						GroupPicsActivity.class);
				Group group = (Group) parent.getAdapter().getItem(position);
				i.putExtra("groupId", group.getGroupId());
				startActivity(i);
			}
		});
		populateGroups();
	}

	public void populateGroups() {
		// Specify which class to query
		ParseQuery<Group> query = ParseQuery.getQuery(Group.class);
		// Specify the object id
		query.findInBackground(new FindCallback<Group>() {

			public void done(List<Group> groupItems, ParseException e) {
				aGroups.clear();
				aGroups.addAll(groupItems);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.compose_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}