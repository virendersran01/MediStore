package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.virtualstudios.medistore.R;

public class AddProductFragment extends Fragment {

    private View rootView;
    private TextView textHeader;
    private MaterialButton buttonAddMedicated, buttonAddCosmetic;
    private View viewStep1, viewStep2, viewStep3, viewStep4, viewStep5;
    private MaterialButton buttonStep2, buttonStep3, buttonStep4, buttonStep5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        initView();
        return rootView;
    }

    private void initView(){
        textHeader = rootView.findViewById(R.id.textHeader);
        buttonAddMedicated = rootView.findViewById(R.id.buttonAddMedicine);
        buttonAddCosmetic = rootView.findViewById(R.id.buttonAddCosmetic);
        viewStep1 = rootView.findViewById(R.id.viewStep1);
        buttonStep2 = viewStep1.findViewById(R.id.buttonStep2);
        viewStep2 = rootView.findViewById(R.id.viewStep2);
        buttonStep3 = viewStep2.findViewById(R.id.buttonNext);
        viewStep3 = rootView.findViewById(R.id.viewStep3);
        buttonStep4 = viewStep3.findViewById(R.id.buttonNext);
        viewStep4 = rootView.findViewById(R.id.viewStep4);
        buttonStep5 = viewStep4.findViewById(R.id.buttonNext);
        viewStep5 = rootView.findViewById(R.id.viewStep5);


        buttonAddMedicated.setOnClickListener(v -> {
            showHideButtonMedicated(false);
            showHideButtonCosmetic(false);
            viewStep1.setVisibility(View.VISIBLE);
            textHeader.setText("Add Medicine");
        });

        buttonStep2.setOnClickListener(v -> {
            viewStep1.setVisibility(View.GONE);
            viewStep2.setVisibility(View.VISIBLE);
        });

        buttonStep3.setOnClickListener(v -> {
            viewStep2.setVisibility(View.GONE);
            viewStep3.setVisibility(View.VISIBLE);
        });

        buttonStep4.setOnClickListener(v -> {
            viewStep3.setVisibility(View.GONE);
            viewStep4.setVisibility(View.VISIBLE);
        });

        buttonStep5.setOnClickListener(v -> {
            viewStep4.setVisibility(View.GONE);
            viewStep5.setVisibility(View.VISIBLE);
        });
    }

    private void showHideButtonMedicated(boolean status){
        if (status){
            buttonAddMedicated.setVisibility(View.VISIBLE);
        }else {
            buttonAddMedicated.setVisibility(View.GONE);
        }
    }

    private void showHideButtonCosmetic(boolean status){
        if (status){
            buttonAddCosmetic.setVisibility(View.VISIBLE);
        }else {
            buttonAddCosmetic.setVisibility(View.GONE);
        }
    }

}