package com.apps.flickit;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddGroupActivity extends Activity {
	
	EditText etGroupName;
	DatePicker dpStartDate;
	DatePicker dpEndDate;
	Button btnSave;
	Button btnStartDate;
	Button btnEndDate;
	
	private int startYear, startMonth, startDay;
	private int endYear, endMonth, endDay;
	
	String groupId = "stubGrpId";
		
	static final int START_DATE_DIALOG_ID = 999;
	static final int END_DATE_DIALOG_ID = 000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		setCurrentDateOnView();
		addListenerOnButton();
		etGroupName = (EditText) findViewById(R.id.etGroupName);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_group, menu);
		return true;
	}
	public void onSaveGroup(MenuItem mi){
		
		Intent intent = new Intent(this, FriendListActivity.class);
		intent.putExtra("user", "Akash");
		intent.putExtra("groupId", groupId);
		intent.putExtra("groupName", etGroupName.getText().toString());
		intent.putExtra("startDate", new StringBuilder().append(startMonth + 1)
				   .append("-").append(startDay).append("-").append(startYear)
				   .append(" ").toString());
		intent.putExtra("endDate", new StringBuilder().append(endMonth + 1)
				   .append("-").append(endDay).append("-").append(endYear)
				   .append(" ").toString());
		startActivity(intent);
		//Toast.makeText(getApplicationContext(), "populateFriendList", Toast.LENGTH_SHORT).show();
	}
	
	public void addListenerOnButton() {
		 
		btnStartDate = (Button) findViewById(R.id.btnStartDate);
 
		btnStartDate.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				showDialog(START_DATE_DIALOG_ID);
 
			}
 
		});
		
		btnEndDate = (Button) findViewById(R.id.btnEndDate);
		 
		btnEndDate.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				showDialog(END_DATE_DIALOG_ID);
 
			}
 
		});
 
	}
	
	public void setCurrentDateOnView() {
		 
		dpStartDate = (DatePicker) findViewById(R.id.dpStartDate);
		dpEndDate = (DatePicker) findViewById(R.id.dpEndDate);
 
		final Calendar c = Calendar.getInstance();
		startYear = c.get(Calendar.YEAR);
		startMonth = c.get(Calendar.MONTH);
		startDay = c.get(Calendar.DAY_OF_MONTH);
 
		// set current date into datepicker
		dpStartDate.init(startYear, startMonth, startDay, null);
		
		
		endYear = startYear;
		endMonth = startMonth;
		endDay = startDay;
		
		// set current date into datepicker
		dpEndDate.init(endYear, endMonth, endDay, null);
 
	}
	

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case START_DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, startDateListener, 
                         startYear, startMonth,startDay);
		case END_DATE_DIALOG_ID:
			return new DatePickerDialog(this, endDateListener, 
                    endYear, endMonth, endDay);
		
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
		 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			startYear = selectedYear;
			startMonth = selectedMonth;
			startDay = selectedDay;
 
			// set selected date into textview
			Toast.makeText(getApplicationContext(), new StringBuilder().append(startMonth + 1)
			   .append("-").append(startDay).append("-").append(startYear)
			   .append(" ").toString(), Toast.LENGTH_SHORT).show();
 
			// set selected date into datepicker also
			dpStartDate.init(startYear, startMonth, startDay, null);
 
		}
	};
	
	private DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
		 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			endYear = selectedYear;
			endMonth = selectedMonth;
			endDay = selectedDay;
 
			// set selected date into textview
			Toast.makeText(getApplicationContext(), new StringBuilder().append(endMonth + 1)
			   .append("-").append(endDay).append("-").append(endYear)
			   .append(" ").toString(), Toast.LENGTH_SHORT).show();
 
			// set selected date into datepicker also
			dpEndDate.init(endYear, endMonth, endDay, null);
 
		}
	};
	 
               
}
