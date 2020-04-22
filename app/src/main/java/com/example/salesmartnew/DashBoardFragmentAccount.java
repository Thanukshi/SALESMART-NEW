package com.example.salesmartnew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashBoardFragmentAccount extends Fragment {

    View accountView;

    public DashBoardFragmentAccount() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        accountView = inflater.inflate(R.layout.fragment_account, container, false);
        return  accountView;
    }
}
