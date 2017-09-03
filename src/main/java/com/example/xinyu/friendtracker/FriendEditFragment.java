package com.example.xinyu.friendtracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FriendEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FriendEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FriendEditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private View v;

    private static final String EXTRA_FRIEND_ID = "com.example.xinyu.friendtracker.contact.id";

    protected static final String LOG_TAG = "ASDFASDF";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ContactDetail> list;
    private OnFragmentInteractionListener mListener;

    private static ContactDetail newContactDetail;
    private static ContactDetail contactDetail;

    private EditText editName, editEmail;
    private Button removeButton;
    private Button cancleButton;
    private Button dateButton;
    private static TextView BirthDate;
    private static final String DIALOG_REMOVE = "remove";




//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FriendEditFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static FriendEditFragment newInstance(String param1, String param2) {
//        FriendEditFragment fragment = new FriendEditFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    public FriendEditFragment() {
        // Required empty public constructor


    }


    public static FriendEditFragment newInstance(UUID pId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_FRIEND_ID, pId);
        FriendEditFragment fragment = new FriendEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UUID id = (UUID) getArguments().getSerializable(EXTRA_FRIEND_ID);
            contactDetail = DataContext.get(getActivity()).getFriend(id);
            Log.e(LOG_TAG, "got id");


        }
    }


    public void showFriendDetail() {
        editName = v.findViewById(R.id.editText);
        editEmail = v.findViewById(R.id.editText4);
        BirthDate = v.findViewById(R.id.textViewDate);
        editName.setText(contactDetail.getName());
        BirthDate.setText(contactDetail.getBirthDate());
        editEmail.setText(contactDetail.getEmail());
        removeButton = v.findViewById(R.id.button2);
        cancleButton=v.findViewById(R.id.button3);
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contactDetail.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contactDetail.setEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                RemoveDialogFragment removeDialog = RemoveDialogFragment.newInstance(contactDetail.getId());
                removeDialog.show(fm, DIALOG_REMOVE);


            }

        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager cancle = getActivity().getSupportFragmentManager();
                // Start FriendEditFragment
                Fragment fragmentFrame = cancle.findFragmentById(R.id.fragmentContainer2);
                if (fragmentFrame != null) {
                    Fragment friendFragment = new FriendFragment();
                    cancle.beginTransaction().replace(R.id.fragmentContainer2, friendFragment)
                            .commit();
                }
            }
    });
        dateButton = v.findViewById(R.id.buttonDate);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendEditFragment.DatePickerFragment newFragment = new FriendEditFragment.DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v = inflater.inflate(R.layout.fragment_friend_edit, container, false);

        showFriendDetail();

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String date = String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year);
            BirthDate.setText(date);
            if (contactDetail==null) {
                newContactDetail.setBirthDate(date);
            } else {
                contactDetail.setBirthDate(date);
            }



        }
    }
}
