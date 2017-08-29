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

    public ArrayAdapter<ContactDetail> getAdapter() {
        return arrayAdapter;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

//        listView = v.findViewById(R.id.friendListView);
//
//        arrayAdapter = new ArrayAdapter<ContactDetail>(getActivity(),
//                android.R.layout.simple_list_item_1,
//                list);

        Log.e(LOG_TAG,"AAAAA");
        list = DataContext.get(getActivity()).getPatients();
        Log.e(LOG_TAG,"BBBBB");
        ArrayAdapter<ContactDetail> adapter =
                new ArrayAdapter<ContactDetail>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        list);
//        setListAdapter(adapter);
        Log.e(LOG_TAG,"AAACCCCCAA");


//        listView.setAdapter(arrayAdapter);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_friend, container, false);

        init();

        return v;
    }


    public void showContacts(Intent data) {
        try {
            Log.e(LOG_TAG,"AAAAA");
            ContactDataManager contactsManager = new ContactDataManager(getActivity(), data);
            Log.e(LOG_TAG,"AAAAA");
            ContactDetail contactDetail = new ContactDetail();
            Log.e(LOG_TAG,"AAAAA");
            String name = "";
            String email = "";
            name = contactsManager.getContactName();
            Log.e(LOG_TAG,"AAAAA");
            email = contactsManager.getContactEmail();
            Log.e(LOG_TAG,"AAAAA");
            contactDetail.setName(name);      Log.e(LOG_TAG,"AAAAA");
            contactDetail.setEmail(email);      Log.e(LOG_TAG,"AAAAA");
            list.add(contactDetail);

            Log.e(LOG_TAG,"AAAAA");
            Log.e(LOG_TAG, contactDetail.getEmail());
            Log.e(LOG_TAG, contactDetail.getName());
            refreshList();
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

    private void refreshList() {
        listView = v.findViewById(R.id.friendListView);
//
//        arrayAdapter = new ArrayAdapter<ContactDetail>(getActivity(),
//                android.R.layout.simple_list_item_1,
//                list);

        Log.e(LOG_TAG,"AAAAA");
        list = DataContext.get(getActivity()).getPatients();
        Log.e(LOG_TAG,"BBBBB");
        ArrayAdapter<ContactDetail> adapter =
                new ArrayAdapter<ContactDetail>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        list);
//        setListAdapter(adapter);
        Log.e(LOG_TAG,"AAACCCCCAA");


        listView.setAdapter(adapter);

    }


    private void init() {


        friendView = v.findViewById(R.id.textView2);

        addButton = v.findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendView.append("success!");
                startActivityForResult(contactPickerIntent, PICK_CONTACTS);

            }
        });
    }




    public void onListItemClick(ListView l , View v, int position, long id) {

        Log.e(LOG_TAG, "entered");
        ContactDetail c = (ContactDetail) (getAdapter().getItem(position));
        Log.e(LOG_TAG, "CLICKED");

        FragmentManager fm = getActivity().getSupportFragmentManager();
        // Start FriendEditFragment
        Fragment fragment2 = fm.findFragmentById(R.id.fragmentContainer2);
        if (fragment2 != null) {
            fragment2 = FriendEditFragment.newInstance(c.getId());
            fm.beginTransaction().add(R.id.fragmentContainer2, fragment2)
                    .commit();
        }
    }




}
