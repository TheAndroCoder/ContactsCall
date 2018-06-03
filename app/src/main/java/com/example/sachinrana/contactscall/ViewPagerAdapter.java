package com.example.sachinrana.contactscall;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachin Rana on 06-02-2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<>();
    List<String> tabtitles = new ArrayList<>();
    public void AddFragments(Fragment fragment,String titles){
        this.fragments.add(fragment);
        this.tabtitles.add(titles);
    }
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles.get(position);
    }
}
