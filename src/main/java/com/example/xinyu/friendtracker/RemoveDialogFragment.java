package com.example.xinyu.friendtracker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public class RemoveDialogFragment extends DialogFragment {

    public static final String EXTRA = "whatever";

    protected static final String LOG_TAG = "ASDFASDF";

    public static RemoveDialogFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA, id);

        RemoveDialogFragment removeDialogFragment = new RemoveDialogFragment();
        removeDialogFragment.setArguments(args);

        return removeDialogFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Are you sure you want to remove?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UUID id = (UUID) getArguments().getSerializable(EXTRA);
                        ContactDetail contactDetail = DataContext.get(getActivity()).getFriend(id);
                        int index = FriendFragment.list.indexOf(contactDetail);
                        FriendFragment.list.remove(index);

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        // Start FriendEditFragment
                        Fragment fragmentFrame = fm.findFragmentById(R.id.fragmentContainer2);
                        if (fragmentFrame != null) {
                            Fragment friendFragment = new FriendFragment();
                            fm.beginTransaction().replace(R.id.fragmentContainer2, friendFragment)
                                    .commit();
                        }
                    }
                })
                .create();
    }




}
