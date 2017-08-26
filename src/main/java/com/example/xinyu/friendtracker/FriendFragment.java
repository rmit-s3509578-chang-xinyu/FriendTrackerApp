package com.example.xinyu.friendtracker;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class FriendFragment extends Fragment{

	private View v;
	private ListView listView;
	private FloatingActionButton addButton;
	private TextView friendView;

	private Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACTS) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(getActivity(), data);
                String name = "";
                String email = "";
                try {
                    name = contactsManager.getContactName();
                    email = contactsManager.getContactEmail();
                } catch (ContactDataManager.ContactQueryException e) {
//                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }
    }

	private void init() {

		listView =  v.findViewById(R.id.friendListView);



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
