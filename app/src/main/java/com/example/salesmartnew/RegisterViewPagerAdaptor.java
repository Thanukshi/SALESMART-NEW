package com.example.salesmartnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class RegisterViewPagerAdaptor extends PagerAdapter {

    Context myContext;
    List<RegisterScreenItem> regScreenItems;

    public RegisterViewPagerAdaptor(Context myContext, List<RegisterScreenItem> regScreenItems) {
        this.myContext = myContext;
        this.regScreenItems = regScreenItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutPhotoReg = inflater.inflate(R.layout.layout_photo_reg, null);

        ImageView imageSlide = layoutPhotoReg.findViewById(R.id.image1_photoReg);


        //title.setText(myListScreen.get(position).getTittle());
        //description.setText(myListScreen.get(position).getDescription());
        imageSlide.setImageResource(regScreenItems.get(position).getAddPhoto());

        container.addView(layoutPhotoReg);
        return layoutPhotoReg;

    }

    @Override
    public int getCount() {
        return regScreenItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }
}
