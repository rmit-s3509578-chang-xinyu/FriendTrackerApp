package com.example.xinyu.friendtracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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

import static android.app.Activity.RESULT_OK;

public class FriendFragment extends Fragment {

    private View v;
    private ListView listView;
    private FloatingActionButton addButton;
    private TextView friendView;

    private Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

    protected static final int PICK_CONTACTS = 100;
    protected static final String LOG_TAG = "ASDFASDF";


    private ArrayList<ContactDetail> list;

    ArrayAdapter<ContactDetail> arrayAdapter;


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


    public void showContacts(Intent data) {
        try {
            ContactDataManager contactsManager = new ContactDataManager(getActivity(), data);
            ContactDetail contactDetail = new ContactDetail();
            String name = "";
            String email = "";
            name = contactsManager.getContactName();
            email = contactsManager.getContactEmail();
            contactDetail.setName(name);
            contactDetail.setEmail(email);
            list.add(contactDetail);
            Log.e(LOG_TAG, contactDetail.getEmail());
            Log.e(LOG_TAG, contactDetail.getName());
            init();
        } catch (ContactDataManager.ContactQueryException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACTS) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(getActivity(), data);


                // Check the SDK version and whether the permission is already granted or not.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PICK_CONTACTS);
                    //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
                    showContacts(data);

                } else {
                    showContacts(data);

                }
            }
        }
    }

    private void init() {


        friendView = v.findViewById(R.id.textView2);

        addButton = v.findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(contactPickerIntent, PICK_CONTACTS);

            }
        });

        listView = v.findViewById(R.id.friendListView);
        list = DataContext.get(getActivity()).getFriend();
        arrayAdapter =
                new ArrayAdapter<ContactDetail>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        list);


        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ContactDetail c = (ContactDetail) (arrayAdapter.getItem(position));
                FragmentManager fm = getActivity().getSupportFragmentManager();
                // Start FriendEditFragment
                Fragment fragmentFrame = fm.findFragmentById(R.id.fragmentContainer2);
                if (fragmentFrame != null) {
                    Fragment editFragment = FriendEditFragment.newInstance(c.getId());
                    fm.beginTransaction().replace(R.id.fragmentContainer2, editFragment)
                            .commit();
                    Log.e(LOG_TAG, c.getId().toString());
                }
            }
        });
    }


}
