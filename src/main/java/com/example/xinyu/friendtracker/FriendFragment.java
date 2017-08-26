package com.example.xinyu.friendtracker;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FriendFragment extends Fragment{

	private View v;
	private ListView listView;
	private FloatingActionButton addButton;
	private TextView friendView;

	private Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

	protected static final int PICK_CONTACTS = 100;
	protected static final String LOG_TAG = "ASDFASDF";


	private ArrayList<String> list = new ArrayList<String>();


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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACTS) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(getActivity(), data);
                String name = "";
                String email = "";


                try {
                    name = contactsManager.getContactName();
//                    email = contactsManager.getContactEmail();
					Log.e(LOG_TAG, name);
                    list.add(name);
                    refreshList();

                } catch (ContactDataManager.ContactQueryException e) {
//                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }
    }

    private void refreshList() {
		listView =  v.findViewById(R.id.friendListView);


		ArrayAdapter<String> arrayAdapter =
				new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_list_item_1,
						list);


		listView.setAdapter(arrayAdapter);
	}

	private void init() {





		friendView = v.findViewById(R.id.textView2);

		addButton =   v.findViewById(R.id.floatingActionButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				friendView.append("success!");
				startActivityForResult(contactPickerIntent, PICK_CONTACTS);

			}
		});
	}


}
