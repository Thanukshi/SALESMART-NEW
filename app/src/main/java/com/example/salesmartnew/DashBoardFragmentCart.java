package com.example.salesmartnew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashBoardFragmentCart extends Fragment {

    View cartView;

    public DashBoardFragmentCart() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cartView = inflater.inflate(R.layout.fragment_cart, container, false);
        return cartView;
    }
}
