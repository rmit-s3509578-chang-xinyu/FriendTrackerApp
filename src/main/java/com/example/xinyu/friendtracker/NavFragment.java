package com.example.xinyu.friendtracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class NavFragment extends Fragment {

	private FrameLayout mainView;
	private FrameLayout friendView;
	private FrameLayout meetingView;
	private FrameLayout settingView;

	private ImageView mainImageView;
	private ImageView friendImageView;
	private ImageView meetingImageView;
	private ImageView settingImageView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_nav, container, false);

		mainView = (FrameLayout) v.findViewById(R.id.mainFrameLayout);
		mainImageView = (ImageView) v.findViewById(R.id.mainImageView);
		mainView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				mainImageView.setImageResource(R.drawable.nav_main_active);
//				friendImageView.setImageResource(R.drawable.nav_forms);
//				meetingImageView.setImageResource(R.drawable.nav_announcement);
//				settingImageView.setImageResource(R.drawable.nav_settings);
				
				createFragment(new MainFragment());
			}
		});

		friendView = (FrameLayout) v.findViewById(R.id.friendFrameLayout);
		friendImageView = (ImageView) v.findViewById(R.id.friendImageView);
		friendView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				mainImageView.setImageResource(R.drawable.nav_main);
//				friendImageView.setImageResource(R.drawable.nav_forms_active);
//				meetingImageView.setImageResource(R.drawable.nav_announcement);
//				settingImageView.setImageResource(R.drawable.nav_settings);
//
				createFragment(new FriendFragment());
			}
		});

		meetingView = (FrameLayout) v.findViewById(R.id.meetingFrameLayout);
		meetingImageView = (ImageView) v.findViewById(R.id.meetingImageView);
		meetingView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				mainImageView.setImageResource(R.drawable.nav_main);
//				friendImageView.setImageResource(R.drawable.nav_forms);
//				meetingImageView
//						.setImageResource(R.drawable.nav_announcement_active);
//				settingImageView.setImageResource(R.drawable.nav_settings);
				
				createFragment(new MeetingFragment());
			}
		});

//		settingView = (FrameLayout) v.findViewById(R.id.settingFrameLayout);
//		settingImageView = (ImageView) v.findViewById(R.id.settingImageView);
//		settingView.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
////				mainImageView.setImageResource(R.drawable.nav_main);
////				friendImageView.setImageResource(R.drawable.nav_forms);
////				meetingImageView.setImageResource(R.drawable.nav_announcement);
////				settingImageView
////						.setImageResource(R.drawable.nav_settings_active);
//
//				createFragment(new SettingFragment());
//			}
//		});

		return v;
	}

	public void createFragment(Fragment f) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fragmentContainer2, f);
		transaction.commit();
	}

}
