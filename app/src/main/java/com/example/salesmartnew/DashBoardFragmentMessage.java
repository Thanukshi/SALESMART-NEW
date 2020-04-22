package com.example.salesmartnew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashBoardFragmentMessage extends Fragment {

    View messageView;


    public DashBoardFragmentMessage() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        messageView = inflater.inflate(R.layout.fragment_message, container,false);
        return messageView;
    }
}
