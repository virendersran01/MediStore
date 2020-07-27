package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virtualstudios.medistore.R;


public class AddMedicineFragment extends Fragment {

    private View rootView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_add_medicine, container, false);
            initView();
        }
        return rootView;
    }

    private void initView(){
        rootView.findViewById(R.id.imageBack).setOnClickListener(view -> requireActivity().onBackPressed());
    }
}