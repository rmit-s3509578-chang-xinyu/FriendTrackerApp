package com.example.xinyu.friendtracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class FriendFragment extends Fragment{

	private View v;
	private ListView listView;
	private FloatingActionButton addButton;
	private TextView friendView;

	protected static final int PICK_CONTACTS = 100;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_friend, container, false);

		init();

		return v;
	}

	private void init() {

		listView =  v.findViewById(R.id.friendListView);



		friendView = v.findViewById(R.id.textView2);

		addButton =   v.findViewById(R.id.floatingActionButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				friendView.append("success!");
			}
		});
	}

	
}
