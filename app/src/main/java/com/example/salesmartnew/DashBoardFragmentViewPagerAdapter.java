package com.example.salesmartnew;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class DashBoardFragmentViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment>lstFragment = new ArrayList<>();
    private final List<String>lstTittle = new ArrayList<>();

    public DashBoardFragmentViewPagerAdapter(FragmentManager fm) {
       super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lstFragment.get(position);
    }

    @Override
    public int getCount() {
        return lstTittle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstTittle.get(position);
    }

    public void AddFragment (Fragment fragment, String Title){
        lstFragment.add(fragment);
        lstTittle.add(Title);
    }
}
