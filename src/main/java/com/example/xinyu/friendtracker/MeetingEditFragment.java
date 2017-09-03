package com.example.xinyu.friendtracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MeetingEditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private EditText meetingTitle;
    private static TextView startTimeTime, startTimeDate, endTime;

    private Button dateButton, timeButton;


    private TextView title, place, time, placeTextView;

    private View v;


    private static final String EXTRA_MEEDING_ID = "com.example.xinyu.friendtracker.meeting.id";
    protected static final String LOG_TAG = "ASDFASDF";


    private static MeetingDetail meetingDetail;

    private static MeetingDetail newMeetingDetail;
    private boolean isNew = false;

    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetingEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingEditFragment newInstance(String param1, String param2) {
        MeetingEditFragment fragment = new MeetingEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public MeetingEditFragment() {
        // Required empty public constructor
        newMeetingDetail = new MeetingDetail();
        isNew = true;
    }


    public static MeetingEditFragment newInstance(UUID pId) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MEEDING_ID, pId);
        MeetingEditFragment fragment = new MeetingEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()  != null) {

            UUID id = (UUID) getArguments().getSerializable(EXTRA_MEEDING_ID);
            meetingDetail = DataContextMeeting.get(getActivity()).getMeeting(id);
            isNew = false;
            Log.e(LOG_TAG, "got id hahahaha");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_meeting_edit, container, false);

        showMeetingDetail();

        return v;


    }



    public void showMeetingDetail() {

        placeTextView = v.findViewById(R.id.placeTextView);


        meetingTitle = v.findViewById(R.id.editText3);
        startTimeDate = v.findViewById(R.id.textViewDate);
        startTimeTime = v.findViewById(R.id.textViewTime);
        if (meetingDetail != null) {
            meetingTitle.setText(meetingDetail.getTitle());
            startTimeDate.setText(meetingDetail.getStartTimeDate());
            startTimeTime.setText(meetingDetail.getStartTimeTime());
        }





        meetingTitle.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (meetingDetail == null) {
                    newMeetingDetail.setTitle(charSequence.toString());
                } else {
                    meetingDetail.setTitle(charSequence.toString());
                }

//                meetingDetail.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        dateButton = v.findViewById(R.id.buttonDate);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });


        timeButton = v.findViewById(R.id.buttonTime);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });



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


        if (isNew) {
            newMeetingDetail.setId(UUID.randomUUID());
            MeetingFragment.meetingList.add(newMeetingDetail);
        }


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



    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
            startTimeTime.setText(time);
            if (meetingDetail==null) {
                newMeetingDetail.setStartTimeTime(time);
            } else {
                meetingDetail.setStartTimeTime(time);
            }
        }
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
            startTimeDate.setText(date);
            if (meetingDetail==null) {
                newMeetingDetail.setStartTimeDate(date);
            } else {
                meetingDetail.setStartTimeDate(date);
            }



        }
    }
}
