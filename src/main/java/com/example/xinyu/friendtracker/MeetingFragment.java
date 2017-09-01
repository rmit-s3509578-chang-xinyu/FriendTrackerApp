package com.example.xinyu.friendtracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MeetingFragment extends Fragment {

	private TextView meetingView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_meeting, container, false);
		init();
		return v;

	}

	private void init() {


		TextView meetingtimeView = (TextView)findViewById(R.id.textView10);

		addButton = v.findViewById(R.id.floatingActionButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult();

			}

			meetingButton.setOncLOCKlISTENER(new View.OnClickListener()){
			 @Override
                     public void onClick(View v){
                    Toast.makeText(meetingView.this,R.string.);
					
                }
			}
        }
		});
}
