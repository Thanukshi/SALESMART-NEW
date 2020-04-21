package com.example.salesmartnew;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class WelcomeViewPageAdaptor extends PagerAdapter {

    Context myContext;
    List<WelcomeScreen> myListScreen;


    public WelcomeViewPageAdaptor(Context myContext, List<WelcomeScreen> myListScreen) {
        this.myContext = myContext;
        this.myListScreen = myListScreen;
    }

    @Override
    public int getCount() {
        return myListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
