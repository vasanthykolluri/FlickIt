package com.apps.flickit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.apps.flickit.networking.MyCustomSender;
import com.apps.flickit.networking.MyUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.parse.ParseInstallation;
import com.parse.PushService;

import android.app.Activity;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddGroupActivity extends FragmentActivity {
	
	EditText etGroupName;
	DatePicker dpStartDate;
	DatePicker dpEndDate;
	Button btnSave;
	Button btnStartDate;
	Button btnEndDate;
	EditText etStartDate;
	EditText etEndDate;
	
	
	String groupId = "stubGrpId";
		
	static final int START_DATE_DIALOG_ID = 999;
	static final int END_DATE_DIALOG_ID = 000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		//setCurrentDateOnView();
		//addListenerOnButton();
		etGroupName = (EditText) findViewById(R.id.etGroupName);
		etStartDate = (EditText)findViewById(R.id.etStartDate);
    	etEndDate = (EditText)findViewById(R.id.etEndDate);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_group, menu);
		return true;
	}
	public void onSaveGroup(MenuItem mi) throws ParseException{

		String groupId = "2727659@N22";
		String groupName = etGroupName.getText().toString();
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = sdf.parse(etStartDate.getText().toString());
		Date endDate = sdf.parse(etEndDate.getText().toString());
		
		Intent intent = new Intent(this, FriendListActivity.class);
		intent.putExtra("user", "Akash");
		intent.putExtra("groupId", groupId);
		intent.putExtra("groupName", groupName);
		intent.putExtra("startDate", etStartDate.getText().toString());
		intent.putExtra("endDate", etEndDate.getText().toString());
		
		// ToDo: update imgUrl
		Date dummy = new Date();
		FlickrClientApp.getParseClient().addGroup(groupId, groupName, "imgUrlDummy", startDate, endDate);
		FlickrClientApp.getParseClient().addUserGroup(FlickrClientApp.getAppOwner().getUserId(), groupId);
		
		startActivity(intent);
	}
	
	public void selectDate(View view) {
        DialogFragment newFragment = new SelectDateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("viewName", new Integer(view.getId()).toString());
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }
    public void populateSetDate(int year, int month, int day, String viewId) {
    	
    	etStartDate = (EditText)findViewById(R.id.etStartDate);
    	etEndDate = (EditText)findViewById(R.id.etEndDate);
    	if(Integer.parseInt(viewId) == R.id.imgStartDate)
    	etStartDate.setText(month+"/"+day+"/"+year);
    	else if(Integer.parseInt(viewId) == R.id.imgEndDate)
    	etEndDate.setText(month+"/"+day+"/"+year);
    }
    @SuppressLint("ValidFragment")
	public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    	@Override
    	public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    	}
    	
    	public void onDateSet(DatePicker view, int yy, int mm, int dd) {
    		Bundle bundle = this.getArguments();
    		//Toast.makeText(getActivity(), "Arguments" + bundle.getString("viewName") , Toast.LENGTH_SHORT).show();
    		populateSetDate(yy, mm+1, dd, bundle.getString("viewName"));
    	}
    }      
}
