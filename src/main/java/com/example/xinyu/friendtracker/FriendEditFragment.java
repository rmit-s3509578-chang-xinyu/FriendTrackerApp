package com.example.xinyu.friendtracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String EXTRA_FRIEND_ID = "com.example.xinyu.friendtracker.contact.id";
private static final String EXTRA_FRIEND_Name="com.example.xinyu.friendtracker.contact.name";
    protected static final String LOG_TAG = "ASDFASDF";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ContactDetail> list;
    private OnFragmentInteractionListener mListener;

    private ContactDetail contactDetail;
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
            Log.e(LOG_TAG, "got id hahahaha");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_edit, container, false);
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
}
