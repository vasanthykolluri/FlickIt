package com.apps.flickit;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apps.flickit.R;


public class ListAdapter extends ArrayAdapter<String> {

	boolean[] itemChecked;
Activity context;
public ListAdapter(Activity context, List<String> emails) {
	super(context, 0, emails);
	this.context = context;
	itemChecked = new boolean[emails.size()];
	// TODO Auto-generated constructor stub
}
private class ViewHolder {
	  TextView email;
	  CheckBox ck1;
	 }
@Override
public View getView(final int position, View convertView, ViewGroup parent) {
	final ViewHolder holder;
	
	LayoutInflater inflater = context.getLayoutInflater();
	String email = getItem(position);
	
	//find or inflate the template
	if (convertView == null) {
		   convertView = inflater.inflate(R.layout.list_item, null);
		   holder = new ViewHolder();
		 
		   holder.email = (TextView) convertView
		     .findViewById(R.id.textView1);
		   holder.ck1 = (CheckBox) convertView
		     .findViewById(R.id.checkBox1);
		 
		   convertView.setTag(holder);
		 
		  }  else {
	    
	   holder = (ViewHolder) convertView.getTag();
	  }
	
	holder.email.setText(email);
	holder.ck1.setChecked(false);
	if (itemChecked[position])
		   holder.ck1.setChecked(true);
		  else
		   holder.ck1.setChecked(false);
		 
		  holder.ck1.setOnClickListener(new OnClickListener() {
		   @Override
		   public void onClick(View v) {
		    // TODO Auto-generated method stub
		    if (holder.ck1.isChecked())
		     itemChecked[position] = true;
		    else
		     itemChecked[position] = false;
		   }
		  });
		 
		  return convertView;
}
}
