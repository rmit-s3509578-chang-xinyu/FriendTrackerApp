package com.example.xinyu.friendtracker;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity implements FriendEditFragment.OnFragmentInteractionListener {


    protected int getLayoutResId() {
        return R.layout.new_layout;
    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment1 = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment1 == null) {
            fragment1 = new NavFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment1)
                    .commit();

        }
        Fragment fragment2 = fm.findFragmentById(R.id.fragmentContainer2);
        if (fragment2 == null) {
            fragment2 = new MainFragment();
            fm.beginTransaction().add(R.id.fragmentContainer2, fragment2)
                    .commit();
        }
    }


    @Override
    public void onBackPressed()
    {
        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

}
