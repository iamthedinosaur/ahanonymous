package com.example.desetad20.ahanonymous;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position){
        if(position == 0)
            return new FragmentChat();
        else if(position == 1)
            return new FragmentQuestions();
        else
            return new FragmentProfile();
    }

    public int getCount(){
        return 3;
    }
}
