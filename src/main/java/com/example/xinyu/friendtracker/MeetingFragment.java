package com.example.xinyu.friendtracker;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;






import java.util.ArrayList;

public class MeetingFragment extends Fragment {



	private View v;
	private FloatingActionButton addButton;
	private TextView friendView;


	ListView meetingListView;

	protected static final String LOG_TAG = "ASDFASDF";


	public static ArrayList<MeetingDetail> list;

	ArrayAdapter<MeetingDetail> arrayAdapter;


	private TextView meetingView;




	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}




	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_meeting, container, false);
		init();
		return v;

	}

	private void init() {


		TextView meetingtimeView = v.findViewById(R.id.textView10);
		meetingListView = v.findViewById(R.id.meetingListView);

		list = DataContextMeeting.get(getActivity()).getMeeting();

		arrayAdapter =
				new ArrayAdapter<MeetingDetail>(getActivity(),
						android.R.layout.simple_list_item_1,
						list);


		meetingListView.setAdapter(arrayAdapter);


		meetingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				MeetingDetail c = (MeetingDetail) (arrayAdapter.getItem(position));
				FragmentManager fm = getActivity().getSupportFragmentManager();
				// Start FriendEditFragment
				Fragment fragmentFrame = fm.findFragmentById(R.id.fragmentContainer2);
				if (fragmentFrame != null) {
					Fragment editFragment = FriendEditFragment.newInstance(c.getId());
					fm.beginTransaction().replace(R.id.fragmentContainer2, editFragment)
							.addToBackStack(null).commit();
					Log.e(LOG_TAG, c.getId().toString());
				}
			}
		});













		addButton = v.findViewById(R.id.floatingActionButton2);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


			}


		});
	}
}
