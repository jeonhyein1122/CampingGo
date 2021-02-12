package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomePagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages=new Fragment[2];
    String[] titles=new String[]{"캠핑","캠핑의 모든것"};
    public HomePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        pages[0]=new Home_First_Fragment();
        pages[1]=new Home_Second_Fragment();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
